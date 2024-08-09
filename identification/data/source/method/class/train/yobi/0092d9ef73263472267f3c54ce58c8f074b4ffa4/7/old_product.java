@Transactional
    @With(NullProjectCheckAction.class)
    public static Result cancelEnroll(String loginId, String proejctName) {
        Project project = Project.findByOwnerAndProjectName(loginId, proejctName);

        User user = UserApp.currentUser();
        if (!ProjectUser.isGuest(project, user)) {
            return badRequest();
        }

        if (User.enrolled(project)) {
            user.cancelEnroll(project);
            NotificationEvent.afterMemberRequest(project, user, RequestState.CANCEL);
        }

        return ok();
    }