class Storage {

  def get(key:String):Option[String] = {
    key match {
      case "/hello" => Some("Hello there!")
      case _ => Some("Not found: " + key)
    }
  }
}
