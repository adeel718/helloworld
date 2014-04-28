package controllers;

import static play.data.Form.form;
import models.User;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("test"));
    }
    
}
