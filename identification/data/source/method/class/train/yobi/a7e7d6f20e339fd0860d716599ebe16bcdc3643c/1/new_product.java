public static List<Milestone> findByProjectId(Long projectId) {
        return Milestone.findMilestones(projectId, StateType.ALL);
    }