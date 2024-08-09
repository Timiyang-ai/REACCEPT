public static Result newUser() {
        Form<User> newUserForm = form(User.class).bindFromRequest();
        validate(newUserForm);
        if (newUserForm.hasErrors())
            return badRequest(signup.render("title.signup", newUserForm));
        else {
            User user = newUserForm.get();
            user.avatarUrl = DEFAULT_AVATAR_URL;
            lockAccountIfSignUpConfirmModeIsUsed(user);
            User.create(hashedPassword(user));
            if(user.isLocked){
                flash(Constants.INFO, "user.signup.requested");
            } else {
                addUserInfoToSession(user);
            }
            return redirect(routes.Application.index());
        }
    }