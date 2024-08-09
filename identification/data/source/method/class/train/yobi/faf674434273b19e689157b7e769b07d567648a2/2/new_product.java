public static String roleOf(String loginId, Project project) {
        User user = User.findByLoginId(loginId);
        if(user == null) {
            return RoleType.ANONYMOUS.getLowerCasedName();
        }

        if(user.isSiteManager()) {
            return RoleType.SITEMANAGER.getLowerCasedName();
        }

        if(user.isAnonymous()) {
           return RoleType.ANONYMOUS.getLowerCasedName();
        } else {
            Role role = Role.findRoleByIds(user.id, project.id);
            if(role == null) {
                return RoleType.GUEST.getLowerCasedName();
            } else {
                // manager or member
                return role.name.toLowerCase();
            }
        }
    }