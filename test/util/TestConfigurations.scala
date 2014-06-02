package util

import db.mongo.MongoDB

object TestConfigurations {
  
  val mongo: Map[String, String] = Map(MongoDB.KEY_DB -> "helloworld_test")

}