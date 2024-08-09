public static List<Milestone> findMilestones(Long projectId,
                                                 StateType state) {
        return findMilestones(projectId, state, DEFAULT_SORTER, Direction.ASC);
    }