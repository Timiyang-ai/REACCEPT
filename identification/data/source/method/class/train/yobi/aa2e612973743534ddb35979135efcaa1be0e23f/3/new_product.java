public static void assignRole(Long userId, Long projectId, Long roleId) {
        ProjectUser projectUser = ProjectUser.findByIds(userId, projectId);

        if (projectUser == null) {
            ProjectUser.create(userId, projectId, roleId);
        } else {
            new ProjectUser(userId, projectId, roleId).update(projectUser.id);
        }
    }