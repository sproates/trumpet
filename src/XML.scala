import javax.servlet.http.{
  HttpServletResponse => Response
}
import scala.xml.{
  Node,
  Unparsed
}

trait XML {
  def cdata(s:String):Node = Unparsed("<![CDATA[" + s + "]]>")

  def xmlHeader(response:Response) = {
    response.setContentType("text/xml")
    response.getWriter().println("""<?xml version="1.0" encoding="UTF-8"?>""")
  }
}
