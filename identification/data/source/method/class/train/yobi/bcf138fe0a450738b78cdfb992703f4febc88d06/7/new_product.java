public static boolean isManager(Long userId, Long projectId) {
        int findRowCount = find.where().eq("user.id", userId)
                .eq("role.id", RoleType.MANAGER.roleType()).eq("project.id", projectId)
                .findRowCount();
        return (findRowCount != 0) ? true : false;
    }