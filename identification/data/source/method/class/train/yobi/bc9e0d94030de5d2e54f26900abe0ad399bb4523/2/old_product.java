public static Result login() {
        Form<User> userForm = form(User.class).bindFromRequest();
        if(userForm.hasErrors()) {
            return badRequest(login.render("title.login", userForm, null));
        }
        User sourceUser = form(User.class).bindFromRequest().get();

        Map<String, String[]> params = request().body().asFormUrlEncoded();
        String redirectUrl = HttpUtil.getFirstValueFromQuery(params, "redirectUrl");

        String loginFormUrl = routes.UserApp.loginForm().url();
        loginFormUrl += "?redirectUrl=" + redirectUrl;

        if (isUseSignUpConfirm()) {
            if (User.findByLoginId(sourceUser.loginId).state == UserState.LOCKED) {
                flash(Constants.WARNING, "user.locked");
                return redirect(loginFormUrl);
            }
        }

        if (User.findByLoginId(sourceUser.loginId).state == UserState.DELETED) {
            flash(Constants.WARNING, "user.deleted");
            return redirect(loginFormUrl);
        }

        User authenticate = authenticateWithPlainPassword(sourceUser.loginId, sourceUser.password);

        if (!authenticate.isAnonymous()) {
            addUserInfoToSession(authenticate);
            if (sourceUser.rememberMe) {
                setupRememberMe(authenticate);
            }

            authenticate.lang = play.mvc.Http.Context.current().lang().code();
            authenticate.update();

            if (StringUtils.isEmpty(redirectUrl)) {
                return redirect(routes.Application.index());
            } else {
                return redirect(redirectUrl);
            }
        }

        flash(Constants.WARNING, "user.login.failed");
        return redirect(routes.UserApp.loginForm());
    }