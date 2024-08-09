@Test
  public void testUsage() throws Exception {

    // argument combinations that should return success
    String[][] goodArgsList = {
        { "--help" }, // print help
        { "create", "--stream", "firststream" }, // create simple stream
//        { "id", "--stream", "firststream" }, // fetch from simple stream
//        { "send", "--stream", "firststream", "--body", "funk" }, // send event to stream
//        { "fetch", "--stream", "firststream", "--consumer", "firstconsumer" }, // fetch from simple stream
    };

    // argument combinations that should lead to failure
    String[][] badArgsList = {
        { },
        { "create", "firststre@m" }, // create stream with illegal name
        { "fetch", "--key" }, // no key
    };

    // test each good combination
    for (String[] args : goodArgsList) {
      LOG.info("Testing: " + Arrays.toString(args));
      Assert.assertNotNull(new StreamClient().execute(args, configuration));
    }
    // test each bad combination
    for (String[] args : badArgsList) {
      LOG.info("Testing: " + Arrays.toString(args));
      Assert.assertNull(new StreamClient().execute(args, configuration));
    }
  }