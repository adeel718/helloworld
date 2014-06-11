package util

import scala.language.implicitConversions

object Implicits {
  implicit def string2Option(s: String): Option[String] = Option(s) 
}