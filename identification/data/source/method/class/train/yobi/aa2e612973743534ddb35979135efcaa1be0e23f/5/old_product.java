public static void assignRole(Long userId, Long projectId, Long roleId) {
        if (find.where().eq("user.id", userId).eq("project.id", projectId)
                .findRowCount() != 0) {
            for (ProjectUser projectUser : find.where().eq("user.id", userId)
                    .eq("project.id", projectId).findList()) {
                projectUser.delete();
            }
        }

        List<RolePermission> rolePermissions = RolePermission
                .findByRole(roleId);
        for (RolePermission rolePermission : rolePermissions) {
            ProjectUser.create(userId, projectId, rolePermission.id);
        }
    }