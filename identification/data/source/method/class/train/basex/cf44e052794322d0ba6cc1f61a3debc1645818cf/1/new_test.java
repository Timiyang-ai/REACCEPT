@Test
  public void event() throws BaseXException {
    // create the event
    session.execute("create event " + NAME);
    // watch event
    for(final ClientSession cs : sessions) {
      cs.watch(NAME, new EventNotifier() {
        @Override
        public void notify(final String data) {
          assertEquals(RETURN, data);
        }
      });
    }
    // fire an event
    session.query("db:event('" + NAME + "', '" + RETURN + "')").execute();

    // all clients unwatch the events
    for(final ClientSession cs : sessions) cs.unwatch(NAME);

    // drop event
    session.execute("drop event " + NAME);
  }