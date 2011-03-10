import java.io.PrintWriter
import javax.servlet.http.{
  HttpServlet, HttpServletRequest => Request,
  HttpServletResponse => Response
}

class Trumpet extends HttpServlet {

  override def doGet(request : Request, response : Response) = {
    response.setContentType("text/xml")
    response.getWriter().println(placeholder)
  }

  val placeholder = <response>Ok</response>
}
