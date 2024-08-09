public static String roleOf(String loginId, Project project) {
        RoleType roleType = RoleType.ANONYMOUS;
        if(loginId == null) {
            return roleType.getLowerCasedName();
        }

        User user = User.findByLoginId(loginId);
        if(user == null) {
            return roleType.getLowerCasedName();
        }

        if(user.isSiteManager()) {
            return RoleType.SITEMANAGER.getLowerCasedName();
        } else if(!user.isAnonymous()) {
            Role role = Role.findRoleByIds(user.id, project.id);
            // manager or member
            if(role != null) {
                return role.name.toLowerCase();
            } else {
                return RoleType.GUEST.getLowerCasedName();
            }
        }
        return roleType.getLowerCasedName();
    }