    @Test
    public void recordExperimentsEvents() throws Exception {
        Map<Experiment.Label, Map<User.ID, List<Event>>> eventList = new HashMap<>();

        thrown.expect(UnsupportedOperationException.class);
        thrown.expectMessage("Not implemented");
        resource.recordExperimentsEvents(applicationName, eventList);
    }