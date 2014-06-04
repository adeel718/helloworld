package controllers;

import static play.data.Form.form;
import models.User;
import models.UserDaoUtils;
import play.data.Form;
import play.i18n.Messages;
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
			//flash("message", "Please enter the user name field and it should not be blank");
			return badRequest(login.render(form));
		}
		
		final User user = form.get();
		final String username = user.getUserName();
		final String password = user.getPassword();
		if(!UserDaoUtils.isUserExist(username, password)) {
			final String msgKey = "error.login.unknown.user";
			form.reject("userName", Messages.get(msgKey));
			form.reject("password", Messages.get(msgKey));
			flash("registerLink", "Are you sure you have registered? Click to register");
			return badRequest(login.render(form));
		}
		
		
		return redirect(routes.Welcome.index(username));
	}
    
}
