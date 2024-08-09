@Transactional
    @With(NullProjectCheckAction.class)
    public static Result enroll(String loginId, String projectName) {
        Project project = Project.findByOwnerAndProjectName(loginId, projectName);

        User user = UserApp.currentUser();
        if (!ProjectUser.isGuest(project, user)) {
            return badRequest();
        }

        if (!User.enrolled(project)) {
            user.enroll(project);
            NotificationEvent.afterMemberRequest(project, user, RequestState.REQUEST);
        }

        return ok();
    }