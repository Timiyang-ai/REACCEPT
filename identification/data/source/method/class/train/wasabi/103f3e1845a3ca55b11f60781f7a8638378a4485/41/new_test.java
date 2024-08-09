    @Test
    public void recordUsersEvents() throws UnsupportedOperationException {
        Map<User.ID, List<Event>> eventList = new HashMap<>();

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Not implemented");
        resource.recordUsersEvents(applicationName, experimentLabel, eventList);
    }