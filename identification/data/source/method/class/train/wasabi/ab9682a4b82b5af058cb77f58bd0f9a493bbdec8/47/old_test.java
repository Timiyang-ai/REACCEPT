    @Test
    public void recordEvents() throws Exception {
        List<Event> listOfEvents = new ArrayList<>();
        Event e = new Event();
        e.setName(Name.valueOf("someEventName"));
        listOfEvents.add(e);
        eventList.setEvents(listOfEvents);

        resource.recordEvents(applicationName, experimentLabel, userID, eventList);
        verify(events).recordEvents(any(Application.Name.class),
                any(Experiment.Label.class), any(User.ID.class), any(EventList.class), any(Set.class));
    }