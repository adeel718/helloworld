package controllers;

import static play.data.Form.form;
import models.User;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return redirect(routes.Application.login());
    }

	public static Result login() {
        return ok(login.render(form(User.class)));
	}

	public static Result loginSubmit() {
		Form<User> form = form(User.class).bindFromRequest();
		if(form.hasErrors() || form.hasGlobalErrors()) {
			flash("message", "Please enter the user name field and it should not be blank");
			return badRequest(login.render(form));
		}
		
		String username = form.get().getUserName();
		return ok(welcome.render(username));
	}
    
}
