public static boolean isAllowedToSettings(String loginId, Project project) {
        if(loginId == null) {
            return false;
        }

        User user = User.findByLoginId(loginId);
        if(user.isAnonymous()) {
            return false;
        }
        if(user.isSiteManager() || ProjectUser.isManager(user.id, project.id)) {
            return true;
        }
        return false;
    }