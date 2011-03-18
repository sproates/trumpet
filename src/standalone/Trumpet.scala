import java.io.{
  FileReader,
  IOException
}
import java.util.Properties;
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{
  ServletContextHandler,
  ServletHolder
}

object Trumpet extends {

  val propsFile = "trumpet.properties"

  lazy val props:Properties = new Properties

  lazy val port = {
    var default = 8080
    var parsedPort:Option[Int] = None
    try {
      parsedPort = Some(Integer.parseInt(props.getProperty("port")))
    } catch {
      case ex:NumberFormatException => println("port is not a number. Using " + default)
    }
    parsedPort.getOrElse(default)
  }

  def main(args:Array[String]) = {
    try {
      props.load(new FileReader(propsFile))
    } catch {
      case ex:IOException => {
        println("Unable to load " + propsFile)
      }
    }
    var servletHolder = new ServletHolder(new TrumpetServlet)
    var server = new Server(port)
    var context = new ServletContextHandler(ServletContextHandler.SESSIONS)
    context.setContextPath("/")
    server.setHandler(context)
    servletHolder.setInitParameter("storage_type", props.getProperty("storage_type"))
    servletHolder.setInitParameter("storage_location", props.getProperty("storage_location"))
    context.addServlet(servletHolder, "/*")
    server.start
    server.join
  }
}
