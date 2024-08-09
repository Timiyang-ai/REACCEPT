@Test
  public void trigger() throws BaseXException {

    // create the trigger
    cs.execute("create trigger " + TRIGGER_NAME);
    // attach half of the clients to the trigger
    for(int i = ccs.length / 2; i < ccs.length; i++) {
      ccs[i].attachTrigger(TRIGGER_NAME, new TriggerNotification() {
        @Override
        public void update(final String data) {
          assertEquals(RETURN_VALUE, data);
        }
      });
    }

    // release a trigger
    cs.trigger("1 to 10", TRIGGER_NAME, RETURN_VALUE);

    // detach all clients attached to trigger beforehand
    for(int i = ccs.length / 2; i < ccs.length; i++) {
      ccs[i].detachTrigger(TRIGGER_NAME);
    }

    // drop trigger
    cs.execute("drop trigger " + TRIGGER_NAME);
  }