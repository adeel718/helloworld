package db.mongo

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import play.api.test.WithApplication
import play.api.test.FakeApplication
import util.TestConfigurations

class MongoDBIntegrationTest extends FlatSpec with Matchers {

  "Mongo DB" should "connect to test database with test config" in 
  	new WithApplication(FakeApplication(additionalConfiguration = TestConfigurations.mongo)) {
		// setup mongo test instance done through application
	  	MongoDB().name shouldBe equal(TestConfigurations.mongo(MongoDB.KEY_DB))
    }
}