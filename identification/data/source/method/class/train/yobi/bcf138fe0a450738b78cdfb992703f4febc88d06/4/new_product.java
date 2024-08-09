public static List<Role> getAllProjectRoles() {
        List<Role> projectRoles = find.where().ne("name", "siteManager").findList();
        return projectRoles;
    }