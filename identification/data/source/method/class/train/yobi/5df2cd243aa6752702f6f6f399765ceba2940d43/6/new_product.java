public static List<Milestone> findMilestones(Long projectId, MilestoneState state) {
        return findMilestones(projectId, state, DEFAULT_SORTER, Direction.ASC);
    }