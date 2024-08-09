public static void assignRole(Long userId, Long projectId, Long roleId) {
        if (find.where().eq("user.id", userId).eq("project.id", projectId)
                .findRowCount() == 0) {
            ProjectUser.create(userId, projectId, roleId);
        } else {
            new ProjectUser(userId, projectId, roleId).update(ProjectUser
                    .findByIds(userId, projectId).id);
        }
    }