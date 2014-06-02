package util

import org.jsoup.nodes.Document
import org.scalatest.Matchers._
import org.jsoup.Jsoup

object JsoupUtils {
  
  def assertFieldPresent(fieldName: String, 
      expectedPlaceholder: String, 
      expectedValue: Option[String] = None)(implicit doc: Document) = {
    val field = getUniqueElement("#" + fieldName) // id == fieldName
    field.attr("placeholder") shouldBe expectedPlaceholder
    val fieldValue = field.`val`
    expectedValue match {
      case None => fieldValue shouldBe empty
      case Some(asExpected) => fieldValue shouldBe asExpected
    }
  }
  
  def getUniqueElement(jsoupSelector: String)
  		(implicit doc: Document): org.jsoup.nodes.Element = {
    val elements = doc.select(jsoupSelector)
    elements should have size 1
    elements.first
  }
  
  def assertErrorPresent(field: String, msg: String)(implicit doc: Document) = {
    val summary = getUniqueElement("#validation-summary").text
    summary should include(msg)
    
    val fieldError = getUniqueElement("#error" + field).text
    fieldError shouldBe msg
  }
}

object JsoupJavaUtils {
  def renderView(viewRenderer: => play.mvc.Content): Document = 
    Jsoup.parse(play.test.Helpers.contentAsString(viewRenderer))
}
