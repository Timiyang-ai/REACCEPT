    @Test
    public void addAll() {
        NavDestination destination = createFirstDestination();
        NavGraph other = createGraphWithDestination(destination);

        NavGraph graph = mNavGraphNavigator.createDestination();
        graph.addAll(other);

        assertThat(destination.getParent(), is(graph));
        assertThat(graph.findNode(FIRST_DESTINATION_ID), is(destination));
        assertThat(other.findNode(FIRST_DESTINATION_ID), nullValue());
    }