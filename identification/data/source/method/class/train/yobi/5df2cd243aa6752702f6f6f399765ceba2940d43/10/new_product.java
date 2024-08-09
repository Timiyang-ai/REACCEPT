public static List<Milestone> findMilestones(Long projectId,
                                                 State state) {
        return findMilestones(projectId, state, DEFAULT_SORTER, Direction.ASC);
    }