import java.io.PrintWriter
import javax.servlet.ServletConfig
import javax.servlet.http.{
  HttpServlet, HttpServletRequest => Request,
  HttpServletResponse => Response
}
import scala.xml.Elem

class TrumpetServlet extends HttpServlet with XML {

  var config:ServletConfig = null;

  override def init(_config:ServletConfig) = {
    super.init(config)
    this.config = _config
  }

  lazy val storage:Option[Storage] = {
    config match {
      case c:ServletConfig => {
        config.getInitParameter("storage_type") match {
          case "file" => Some(new FileStorage(config.getInitParameter("storage_location")))
          case "dummy" => Some(new DummyStorage)
          case _ => None
        }
      }
      case _ => None
    }
  }

  def displayMessage(message:Elem, response:Response) = {
    xmlHeader(response)
    response.getWriter.println(message)
  }

  override def doGet(request:Request, response:Response) = {
    storage match {
      case Some(s) => displayMessage(getFromStorage(s, request.getPathInfo), response)
      case None => displayMessage(errorXML("storage is incorrectly configured"), response)
    }
  }

  private def getFromStorage(s:Storage, key:String) = {
    s.get(getKeyName(key)) match {
      case Some(d) => doGetSuccess(d)
      case None => errorXML("key not found")
    }
  }

  def errorXML(error:String) =
    <error>
      <message>{error}</message>
    </error>

  def doGetSuccess(data:String) =
    <response>
      <data>{cdata(data)}</data>
    </response>

  def getKeyName(s:String) = {
    s.trim match {
      case x:String if x.startsWith("/") => x.substring(1)
      case _ => s
    }
  }
}
