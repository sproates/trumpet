class FileStorage(_path:String) extends Storage {

  var path:String = _path

  def get(key:String):Option[String] = {
    None
  }
}
