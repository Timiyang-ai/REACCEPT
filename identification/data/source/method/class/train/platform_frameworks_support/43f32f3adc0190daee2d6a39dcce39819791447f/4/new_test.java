    @Test
    public void putAction() {
        NavDestination destination = new NavDestination(mock(Navigator.class));
        NavAction action = new NavAction(DESTINATION_ID);
        destination.putAction(ACTION_ID, action);

        assertThat(destination.getAction(ACTION_ID), is(action));
    }