package controllers;

import static play.data.Form.form;
import models.User;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	User user = new User();
    	user.setEmail("asgga@m.com");
    	user.getEmail();
        return ok(index.render("test"));
    }

	public static Result login() {
        return ok(login.render(""));
	}

	public static Result loginSubmit(String username) {
		if(username.trim().isEmpty()) {
			flash("message", "Username should not be empty.");
			return ok(login.render(username));
		}

		return ok(welcome.render(username));
	}
    
}
