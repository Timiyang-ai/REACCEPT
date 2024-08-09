  @Test
  public void incEventsReceived_incrementsTheEventsReceivedStat() {
    int eventsReceivedStatId = 33;

    when(statisticsType.nameToId(EVENTS_RECEIVED_STAT_NAME))
        .thenReturn(eventsReceivedStatId);

    gatewayReceiverStats = createGatewayReceiverStats(factory, ownerName, registry);

    int delta = 99;

    gatewayReceiverStats.incEventsReceived(delta);

    verify(statistics).incLong(eventsReceivedStatId, delta);
  }