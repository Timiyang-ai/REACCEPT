public static List<Milestone> findOpenMilestones(Long projectId) {
        return Milestone.findMilestones(projectId, MilestoneState.OPEN);
    }