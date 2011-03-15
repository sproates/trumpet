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

  def main(args:Array[String]) = {
    var port = 8080
    try {
      var props = new Properties
      props.load(new FileReader(propsFile))
      var parsedPort = Integer.parseInt(props.getProperty("port"))
      port = parsedPort
    } catch {
      case ex:IOException => println("Unable to load " + propsFile)
      case ex:NumberFormatException => println("port is not a number. Using " + port)
      case ex => println("Unknown error: " + ex.getMessage)
    }
    var server = new Server(port)
    var context = new ServletContextHandler(ServletContextHandler.SESSIONS)
    context.setContextPath("/")
    server.setHandler(context)
    context.addServlet(new ServletHolder(new TrumpetServlet()), "/*")
    server.start()
    server.join()
  }
}
