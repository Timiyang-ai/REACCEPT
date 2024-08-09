@Transactional
    public static Result enroll(String loginId, String projectName) {
        Project project = Project.findByOwnerAndProjectName(loginId, projectName);
        if(project == null) {
            return badRequest();
        }

        User user = UserApp.currentUser();
        if (user.isAnonymous()) {
            return badRequest();
        }

        user.enroll(project);
        NotificationEvent.afterMemberRequest(project, user, RequestState.REQUEST, routes.ProjectApp.members(loginId, projectName).url());
        return ok();
    }