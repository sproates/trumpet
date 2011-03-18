import java.io.PrintWriter
import javax.servlet.ServletConfig
import javax.servlet.http.{
  HttpServlet, HttpServletRequest => Request,
  HttpServletResponse => Response
}

class TrumpetServlet extends HttpServlet with XML {

  var storage:Option[Storage] = None;

  override def init(config:ServletConfig) = {
    super.init(config)
    println("init")
    var storageType = config.getInitParameter("storage_type")
    var storageLocation = config.getInitParameter("storage_location")
    storage = storageType match {
      case "file" => Some(new FileStorage(storageLocation))
      case "dummy" => Some(new DummyStorage)
      case _ => None
    }
    storage match {
      case Some(s) => println("storage is set up")
      case None => println("no storage set up")
    }
    println("storage_type = " + storageType)
    println("storage_location = " + storageLocation)
  }

  def displayError(error:String, response:Response) = {
    xmlHeader(response)
    response.getWriter.println(errorXML(error))
  }

  def errorXML(error:String) =
    <error>
      <message>{error}</message>
    </error>

  override def doGet(request:Request, response:Response) = {
    storage match {
      case Some(s) => {
        var key = getKeyName(request.getPathInfo)
        var xml = s.get(key) match {
          case Some(d) => doGetSuccess(key, d)
          case None => doGetFailure(key)
        }
        xmlHeader(response)
        response.getWriter.println(xml)
      }
      case None => displayError("storage is incorrectly configured", response)
    }
  }

  def doGetSuccess(key:String, data:String) =
    <response>
      <key>{key}</key>
      <data>{cdata(data)}</data>
    </response>

  def doGetFailure(key:String) =
    <response>
      <key>{key}</key>
      <error>
        <message>key not found</message>
      </error>
    </response>

  def getKeyName(s:String):String = {
    var key = s.trim();
    if(key.startsWith("/")) {
      key = key.substring(1)
    }
    key
  }
}
