package util

import db.mongo.MongoDB
import scala.collection.JavaConversions._

object TestConfigurations {
  val testDBName = "helloworld_test"
    
  val mongo: Map[String, String] = Map(MongoDB.KEY_DB -> testDBName)
  
  val mongoForJava: java.util.Map[String, String] = java.util.Collections.unmodifiableMap(mongo)
}