  @AfterClass public static void stop() throws Exception {
    try {
      LOG.info("stop local cluster");
      angelClient.stop();
    } catch (Exception x) {
      LOG.error("stop failed ", x);
      throw x;
    }
  }