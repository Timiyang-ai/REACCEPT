public static String roleOf(String loginId, Project project) {
        User user = User.findByLoginId(loginId);
       return roleOf(user, project);
    }