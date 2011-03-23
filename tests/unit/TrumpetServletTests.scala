import org.junit.Assert._
import org.junit.{
  Test,
  Before
}
import org.scalatest.junit.JUnitSuite

class TrumpetServletTests extends JUnitSuite {

  var servlet = new TrumpetServlet

  @Before def initialize() = {}

  @Test def getKeyNameWithPreceedingSlash() = {
    var keyName = servlet.getKeyName("/123abc123ABC")
    assertEquals("123abc123ABC", keyName)
  }
}
