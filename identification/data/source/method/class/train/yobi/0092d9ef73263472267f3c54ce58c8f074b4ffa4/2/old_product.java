@Transactional
    public static Result cancelEnroll(String loginId, String proejctName) {
        Project project = Project.findByOwnerAndProjectName(loginId, proejctName);
        if(project == null) {
            return badRequest();
        }

        User user = UserApp.currentUser();
        if (!ProjectUser.isGuest(project, user)) {
            return badRequest();
        }

        if (User.enrolled(project)) {
            user.cancelEnroll(project);
            NotificationEvent.afterMemberRequest(project, user, RequestState.CANCEL, routes.ProjectApp.members(loginId, proejctName).url());
        }

        return ok();
    }