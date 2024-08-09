public static List<User> findUsersByProject(Long projectId) {
        return find.where().eq("projectUser.project.id", projectId)
                .ne("projectUser.role.id", RoleType.SITEMANAGER.roleType())
                .orderBy().asc("name")
                .findList();
    }