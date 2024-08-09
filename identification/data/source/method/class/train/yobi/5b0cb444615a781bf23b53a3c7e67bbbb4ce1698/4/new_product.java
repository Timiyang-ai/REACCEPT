public static boolean isOnlyManager(Long userId) {
        List<Project> projects = find.select("id").select("name").where()
                .eq("projectUser.user.id", userId)
                .eq("projectUser.role.id", RoleType.MANAGER.roleType())
                .findList();

        for (Project project : projects) {
            if (ProjectUser.checkOneMangerPerOneProject(userId, project.id)) {
                return true;
            }
        }
        return false;
    }