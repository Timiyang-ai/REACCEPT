public static List<Milestone> findMilestones(Long projectId,
                                                 MilestoneState state, String sort, Direction direction) {
        OrderParams orderParams = new OrderParams().add(sort, direction);

        SearchParams searchParams = new SearchParams().add("project.id",
            projectId, Matching.EQUALS);
        if (state == null) {
            state = MilestoneState.ALL;
        }
        switch (state) {
            case OPEN:
                searchParams.add("completionRate", 100, Matching.LT);
                break;
            case CLOSED:
                searchParams.add("completionRate", 100, Matching.EQUALS);
                break;
        }
        return FinderTemplate.findBy(orderParams, searchParams, find);
    }