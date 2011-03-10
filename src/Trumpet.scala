import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{
  ServletContextHandler,
  ServletHolder
}

object Trumpet extends {
  def main(args:Array[String]) = {
    var server = new Server(8080)
    var context = new ServletContextHandler(ServletContextHandler.SESSIONS)
    context.setContextPath("/")
    server.setHandler(context)
    context.addServlet(new ServletHolder(new TrumpetServlet()), "/*")
    server.start()
    server.join()
  }
}
