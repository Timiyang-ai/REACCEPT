    @Test
    public void clear() {
        NavDestination destination = createFirstDestination();
        NavDestination secondDestination = createSecondDestination();
        NavGraph graph = createGraphWithDestinations(destination, secondDestination);

        graph.clear();
        assertThat(destination.getParent(), nullValue());
        assertThat(graph.findNode(FIRST_DESTINATION_ID), nullValue());
        assertThat(secondDestination.getParent(), nullValue());
        assertThat(graph.findNode(SECOND_DESTINATION_ID), nullValue());
    }