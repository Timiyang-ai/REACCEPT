public static Result cancelEnroll(String loginId, String proejctName) {
        Project project = Project.findByOwnerAndProjectName(loginId, proejctName);
        if(project == null) {
            return badProject(loginId, proejctName);
        }

        User user = UserApp.currentUser();
        if(user.isAnonymous()) {
            flash(Constants.WARNING, "user.login.alert");
            return redirect(routes.UserApp.loginForm());
        }

        user.cancelEnroll(project);
        addNotificationEvent(
                project,
                user,
                RequestState.CANCEL,
                routes.ProjectApp.members(loginId, proejctName));
        return redirect(request().getHeader(Http.HeaderNames.REFERER));
    }