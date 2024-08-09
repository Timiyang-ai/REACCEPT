public static List<Milestone> findByProjectId(Long projectId) {
        return Milestone.findMilestones(projectId, MilestoneState.ALL.state());
    }