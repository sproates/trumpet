import java.io.PrintWriter
import javax.servlet.http.{
  HttpServlet, HttpServletRequest => Request,
  HttpServletResponse => Response
}

class TrumpetServlet extends HttpServlet {

  val storage = new Storage

  override def doGet(request:Request, response:Response) = {
    xmlHeader(response)
    response.getWriter().println(respondToGet(storage.get(request.getPathInfo)))
  }

  override def doPost(request:Request, response:Response) = {
    xmlHeader(response)
    response.getWriter().println(<error>Method not supported</error>);
  }

  override def doPut(request:Request, response:Response) = {
    xmlHeader(response)
    response.getWriter().println(<error>Method not supported</error>);
  }

  override def doDelete(request:Request, response:Response) = {
    xmlHeader(response)
    response.getWriter().println(<error>Method not supported</error>);
  }

  def xmlHeader(response:Response) = {
    response.setContentType("text/xml")
    response.getWriter().println("""<?xml version="1.0" encoding="UTF-8"?>""")
  }

  def respondToGet(data:Option[String]) = {
    data match {
      case None => <error>Not found</error>
      case Some(value) => <ok>{value}</ok>
    }
  }
}
