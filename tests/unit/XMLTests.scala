import org.junit.Assert._
import org.junit.{
  Test,
  Before
}
import org.scalatest.junit.JUnitSuite
import scala.xml.Unparsed

class mockXML extends XML {}

class XMLTests extends JUnitSuite {

  var xml = new mockXML

  @Before def initialize() = {}

  @Test def cdataWithNullParam() = {
    var cdata = xml.cdata(null)
    assertEquals("<![CDATA[]]>", cdata.toString)
  }
}
