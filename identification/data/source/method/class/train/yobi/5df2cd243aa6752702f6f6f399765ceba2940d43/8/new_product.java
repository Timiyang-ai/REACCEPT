public static List<Milestone> findMilestones(Long projectId,
                                                 State state, String sort, Direction direction) {
        OrderParams orderParams = new OrderParams().add(sort, direction);
        SearchParams searchParams = new SearchParams().add("project.id", projectId, Matching.EQUALS);
        if (state == null) {
            state = State.ALL;
        }
        switch (state) {
            case OPEN:
                searchParams.add("numOpenIssues", 0, Matching.GT);
                break;
            case CLOSED:
                searchParams.add("numOpenIssues", 0, Matching.EQUALS);
                break;
        }
        return FinderTemplate.findBy(orderParams, searchParams, find);
    }