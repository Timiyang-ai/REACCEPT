@Test
  public void testWaitForever() {
    BspEvent event = new PredicateLock(getStubProgressable());
    Thread signalThread = new SignalThread(event);
    signalThread.start();
    event.waitForever();
    try {
      signalThread.join();
    } catch (InterruptedException e) {
    }
    assertTrue(event.waitMsecs(0));
  }