public static List<Milestone> findByProjectId(Long projectId) {
        return find.where().eq("projectId", projectId).findList();
    }