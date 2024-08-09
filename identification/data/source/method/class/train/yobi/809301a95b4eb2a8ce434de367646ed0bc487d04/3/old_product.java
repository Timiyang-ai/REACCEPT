public static List<Milestone> findClosedMilestones(Long projectId) {
        return Milestone.findMilestones(projectId, MilestoneState.CLOSED);
    }