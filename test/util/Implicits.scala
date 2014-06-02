package util

object Implicits {
  implicit def string2Option(s: String): Option[String] = Option(s) 
}