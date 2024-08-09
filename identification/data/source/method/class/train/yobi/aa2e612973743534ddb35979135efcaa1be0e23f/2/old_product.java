public static ProjectUser findByIds(Long userId, Long projectId) {
        return find.where().eq("user.id", userId).eq("project.id", projectId)
                .findUnique();
    }