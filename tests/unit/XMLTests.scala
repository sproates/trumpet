import com.mockrunner.mock.web.WebMockObjectFactory
import org.junit.Assert._
import org.junit.{
  Test,
  Before
}
import org.scalatest.junit.JUnitSuite
import scala.xml.Unparsed

class mockXML extends XML {}

class XMLTests extends JUnitSuite {

  @Before def initialize() = {}

  @Test def cdataWithNullParam() = {
    var xml = new mockXML
    var cdata = xml.cdata(null)
    assertEquals("<![CDATA[]]>", cdata.toString)
  }

  @Test def xmlHeaderSetsXMLContentType() = {
    var xml = new mockXML
    var factory = new WebMockObjectFactory
    var response = factory.createMockResponse
    xml.xmlHeader(response)
    assertEquals("text/xml", response.getContentType)
  }

  @Test def xmlHeaderSendsXMLOpener() = {
    var xml = new mockXML
    var factory = new WebMockObjectFactory
    var response = factory.createMockResponse
    xml.xmlHeader(response)
    assert(response.getOutputStreamContent.startsWith("""<?xml version="1.0" encoding="UTF-8"?>"""))
  }
}
