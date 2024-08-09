public static Result importForm() {
        if (UserApp.currentUser().isAnonymous()) {
            flash(Constants.WARNING, "user.login.alert");
            return redirect(routes.UserApp.loginForm());
        } else {
            return ok(importing.render("title.newProject", form(Project.class)));
        }
    }