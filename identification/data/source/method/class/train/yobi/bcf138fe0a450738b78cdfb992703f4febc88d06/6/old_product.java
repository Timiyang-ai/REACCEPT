public static List<Role> getAllProjectRoles() {
        List<Role> projectRoles = find.where().ne("id", SITEMANAGER)
                .findList();
        return projectRoles;
    }