public static List<ProjectUser> findMemberListByProject(Long projectId) {
        return find
            .fetch("user", "loginId")
            .fetch("role", "name")
            .where()
                .eq("project.id", projectId)
            .findList();
    }