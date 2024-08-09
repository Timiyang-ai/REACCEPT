public static List<Milestone> findMilestones(Long projectId,
                                                 State state, String sort, Direction direction) {
        OrderParams orderParams = new OrderParams().add(sort, direction);
        SearchParams searchParams = new SearchParams().add("project.id", projectId, Matching.EQUALS);
        if (state != null && state != State.ALL) {
            searchParams.add("state", state, Matching.EQUALS);
        }
        return FinderTemplate.findBy(orderParams, searchParams, find);
    }