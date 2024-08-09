public static List<Role> getAllProjectRoles() {
        List<Role> projectRoles = find.all();
        projectRoles.remove(Role.findByName("siteManager"));
        return projectRoles;
    }