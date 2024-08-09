    @Test
    public void buildDeepLinkIds() {
        NavDestination destination = new NavDestination(mock(Navigator.class));
        destination.setId(DESTINATION_ID);
        int parentId = 2;
        NavGraph parent = new NavGraph(mock(Navigator.class));
        parent.setId(parentId);
        destination.setParent(parent);
        int[] deepLinkIds = destination.buildDeepLinkIds();
        assertThat(deepLinkIds.length, is(2));
        assertThat(deepLinkIds[0], is(parentId));
        assertThat(deepLinkIds[1], is(DESTINATION_ID));
    }