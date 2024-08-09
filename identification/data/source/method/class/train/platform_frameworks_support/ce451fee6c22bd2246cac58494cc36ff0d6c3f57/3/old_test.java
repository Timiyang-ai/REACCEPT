    @Test
    public void addDestination() {
        NavDestination destination = createFirstDestination();
        NavGraph graph = createGraphWithDestination(destination);

        assertThat(destination.getParent(), is(graph));
        assertThat(graph.findNode(FIRST_DESTINATION_ID), is(destination));
    }