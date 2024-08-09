  @Test
  public void resetConnectBackoff() throws Exception {
    SocketAddress addr = mock(SocketAddress.class);
    createInternalSubchannel(addr);

    // Move into TRANSIENT_FAILURE to schedule reconnect
    internalSubchannel.obtainActiveTransport();
    assertExactCallbackInvokes("onStateChange:CONNECTING");
    verify(mockTransportFactory)
        .newClientTransport(
            eq(addr),
            eq(createClientTransportOptions()),
            isA(TransportLogger.class));
    transports.poll().listener.transportShutdown(Status.UNAVAILABLE);
    assertExactCallbackInvokes("onStateChange:" + UNAVAILABLE_STATE);

    // Save the reconnectTask
    FakeClock.ScheduledTask reconnectTask = null;
    for (FakeClock.ScheduledTask task : fakeClock.getPendingTasks()) {
      if (task.command.toString().contains("EndOfCurrentBackoff")) {
        assertNull("There shouldn't be more than one reconnectTask", reconnectTask);
        assertFalse(task.isDone());
        reconnectTask = task;
      }
    }
    assertNotNull("There should be at least one reconnectTask", reconnectTask);

    internalSubchannel.resetConnectBackoff();

    verify(mockTransportFactory, times(2))
        .newClientTransport(
            eq(addr),
            eq(createClientTransportOptions()),
            isA(TransportLogger.class));
    assertExactCallbackInvokes("onStateChange:CONNECTING");
    assertTrue(reconnectTask.isCancelled());

    // Simulate a race between cancel and the task scheduler. Should be a no-op.
    reconnectTask.command.run();
    assertNoCallbackInvoke();
    verify(mockTransportFactory, times(2))
        .newClientTransport(
            eq(addr),
            eq(createClientTransportOptions()),
            isA(TransportLogger.class));
    verify(mockBackoffPolicyProvider, times(1)).get();

    // Fail the reconnect attempt to verify that a fresh reconnect policy is generated after
    // invoking resetConnectBackoff()
    transports.poll().listener.transportShutdown(Status.UNAVAILABLE);
    assertExactCallbackInvokes("onStateChange:" + UNAVAILABLE_STATE);
    verify(mockBackoffPolicyProvider, times(2)).get();
    fakeClock.forwardNanos(10);
    assertExactCallbackInvokes("onStateChange:CONNECTING");
    assertEquals(CONNECTING, internalSubchannel.getState());
  }