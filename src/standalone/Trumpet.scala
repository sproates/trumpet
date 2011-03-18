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

  lazy val port = {
    var default = 8080
    var parsedPort:Option[Int] = None
    try {
      var props = new Properties
      props.load(new FileReader(propsFile))
      parsedPort = Some(Integer.parseInt(props.getProperty("port")))
    } catch {
      case ex:IOException => println("Unable to load " + propsFile)
      case ex:NumberFormatException => println("port is not a number. Using " + default)
    }
    parsedPort.getOrElse(default)
  }

  def main(args:Array[String]) = {
    var server = new Server(port)
    var context = new ServletContextHandler(ServletContextHandler.SESSIONS)
    context.setContextPath("/")
    server.setHandler(context)
    context.addServlet(new ServletHolder(new TrumpetServlet), "/*")
    server.start
    server.join
  }
}
