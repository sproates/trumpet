import javax.servlet.http.{
  HttpServletResponse => Response
}
import scala.xml.{
  Node,
  Text,
  Unparsed
}

trait XML {
  def cdata(s:String):Node = {
    s match {
      case null => Unparsed("<![CDATA[]]>")
      case _ => Unparsed("<![CDATA[" + s + "]]>")
    }
  }

  def xmlHeader(response:Response) = {
    response.setContentType("text/xml")
    response.getWriter().println("""<?xml version="1.0" encoding="UTF-8"?>""")
  }
}
