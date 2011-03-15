class DummyStorage extends Storage {

  def get(key:String):Option[String] = {
    key match {
      case "hello" => Some("Hello there!")
      case _ => None
    }
  }
}
