package views;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import helper.TestSetup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import play.api.templates.Html;
import play.mvc.Http.Context;

public class WelcomeViewUnitTest {

	@Test
	public void welcomeViewShouldGreetUser() {
		final String username = "myUser";
		running(fakeApplication(), new Runnable() {
			@Override
			public void run() {
				Context.current.set(TestSetup.testHttpContext());
					final Html html = views.html.welcome.render(username);
					final Document doc = Jsoup.parse(contentAsString(html));
					assertThat(doc.select("#greeting").text()).isEqualTo("Hello " + username);
			}
		});
	}
}
