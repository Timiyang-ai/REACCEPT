public static List<Role> getActiveRoles() {
        List<Role> projectRoles = find.where().eq("active", true)
                .findList();
        return projectRoles;
    }