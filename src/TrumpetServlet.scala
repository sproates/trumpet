import java.io.PrintWriter
import javax.servlet.http.{
  HttpServlet, HttpServletRequest => Request,
  HttpServletResponse => Response
}

class TrumpetServlet extends HttpServlet with XML {

  lazy val storage:Storage = new DummyStorage

  override def doGet(request:Request, response:Response) = {
    var key = getKeyName(request.getPathInfo)
    var xml = {
      storage.get(key) match {
        case Some(d) => doGetSuccess(key, d)
        case None => doGetFailure(key)
      }
    }
    xmlHeader(response)
    response.getWriter.println(xml)
  }

  def xmlHeader(response:Response) = {
    response.setContentType("text/xml")
    response.getWriter().println("""<?xml version="1.0" encoding="UTF-8"?>""")
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
