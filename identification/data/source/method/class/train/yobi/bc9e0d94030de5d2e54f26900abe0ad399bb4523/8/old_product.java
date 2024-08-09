public static Result login() {
        Form<User> userForm = form(User.class).bindFromRequest();
        if(userForm.hasErrors()) {
            return badRequest(login.render("title.login", userForm));
        }
        User sourceUser = form(User.class).bindFromRequest().get();

        if (isUseSignUpConfirm()) {
            if (User.findByLoginId(sourceUser.loginId).isLocked == true ) {
                flash(Constants.WARNING, "user.locked");
                return redirect(routes.UserApp.loginForm());
            }
        }
        User authenticate = authenticateWithPlainPassword(sourceUser.loginId, sourceUser.password);

        if (authenticate != null) {
            addUserInfoToSession(authenticate);
            if (sourceUser.rememberMe) {
                setupRememberMe(authenticate);
            }
            return redirect(routes.UserApp.userInfo(authenticate.loginId, "own", DAYS_AGO, "projects"));
        }

        flash(Constants.WARNING, "user.login.failed");
        return redirect(routes.UserApp.loginForm());
    }