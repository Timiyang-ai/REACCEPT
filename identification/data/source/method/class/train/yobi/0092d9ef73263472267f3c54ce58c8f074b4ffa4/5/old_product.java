public static Result enroll(String loginId, String projectName) {
        Project project = Project.findByOwnerAndProjectName(loginId, projectName);
        if(project == null) {
            return badProject(loginId, projectName);
        }

        User user = UserApp.currentUser();
        if(user.isAnonymous()) {
            flash(Constants.WARNING, "user.login.alert");
            return redirect(routes.UserApp.loginForm());
        }

        user.enroll(project);
        addNotificationEvent(
                project,
                user,
                RequestState.REQUEST,
                routes.ProjectApp.members(loginId, projectName));
        return redirect(request().getHeader(Http.HeaderNames.REFERER));
    }