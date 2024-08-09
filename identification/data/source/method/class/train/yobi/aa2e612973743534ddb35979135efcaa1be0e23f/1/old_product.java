public static ProjectUser findByIds(Long userId, Long projectId,
            Long rolePermissionId) {
        return find.where().eq("user.id", userId).eq("project.id", projectId)
                .eq("rolePermission.id", rolePermissionId).findUnique();
    }