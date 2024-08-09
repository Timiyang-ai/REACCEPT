@AfterClass
  public static void afterClass() throws IOException, InterruptedException {
    if (gcdHelper != null) {
      gcdHelper.stop();
    }
  }