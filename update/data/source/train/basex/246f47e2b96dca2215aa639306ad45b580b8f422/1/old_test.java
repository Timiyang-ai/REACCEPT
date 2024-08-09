@Test
  public void event() throws BaseXException {

    // create the event
    cs.execute("create event " + EVENT_NAME);
    // watch event with half of the clients
    for(int i = ccs.length / 2; i < ccs.length; i++) {
      ccs[i].watchEvent(EVENT_NAME, new EventNotification() {
        @Override
        public void update(final String data) {
          assertEquals(RETURN_VALUE, data);
        }
      });
    }

    // release an event
    cs.event("1 to 10", EVENT_NAME, RETURN_VALUE);

    // all clients unwatch the events
    for(int i = ccs.length / 2; i < ccs.length; i++) {
      ccs[i].unwatchEvent(EVENT_NAME);
    }

    // drop event
    cs.execute("drop event " + EVENT_NAME);
  }