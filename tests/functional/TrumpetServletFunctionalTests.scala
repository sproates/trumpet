import com.mockrunner.mock.web.WebMockObjectFactory
import com.mockrunner.servlet.ServletTestModule
import javax.servlet.http.HttpServlet
import org.junit.Assert._
import org.junit.{
  Before,
  Test
}
import org.scalatest.junit.JUnitSuite

class TrumpetServletFunctionalTests extends JUnitSuite {

  var testModule:ServletTestModule = null;
  var servlet:HttpServlet = null;

  @Before def initialize {
    testModule = new ServletTestModule(new WebMockObjectFactory)
    servlet = testModule.createServlet((new TrumpetServlet).getClass)
  }

  @Test def basicFailureCheck() = {
    testModule.doGet
    var output = testModule.getOutput
    assert(output.contains("<error>"))
  }
}
