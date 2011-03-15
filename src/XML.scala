import scala.xml.{
  Node,
  Unparsed
}

trait XML {
  def cdata(s:String):Node = Unparsed("<![CDATA[" + s + "]]>")
}
