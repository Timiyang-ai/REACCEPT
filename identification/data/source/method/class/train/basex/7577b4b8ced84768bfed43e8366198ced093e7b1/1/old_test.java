@Test
  public void createTest() throws Exception {
    // first thread
    final Thread t1 = new Thread() {
      @Override
      public void run() {
        try {
          session1.execute(new CreateDB(NAME, FILE));
        } catch(final IOException ex) {
        }
      }
    };
    // second thread
    final Thread t2 = new Thread() {
      @Override
      public void run() {
        // wait until first command is running
        Performance.sleep(100);
        try {
          session2.execute(new CreateDB(NAME, FILE));
          fail(FILE + " should still be locked.");
        } catch(final IOException ex) {
        }
      }
    };

    // start and join threads
    t1.start();
    t2.start();
    t1.join();
    t2.join();

    // opens DB in session2 for further tests
    session2.execute(new Open(NAME));

    if(server.context.datas.pins(NAME) != 2) fail("test failed conCreate");
  }