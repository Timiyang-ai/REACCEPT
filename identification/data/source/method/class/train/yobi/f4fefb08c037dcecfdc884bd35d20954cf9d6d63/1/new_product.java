public static boolean isAllowedToSettings(String loginId, Project project) {
        if(loginId == null) {
            return false;
        }

        User user = User.findByLoginId(loginId);
        if(user.isAnonymous()) {
            return false;
        }
        return user.isSiteManager() || ProjectUser.isManager(user.id, project.id);
    }