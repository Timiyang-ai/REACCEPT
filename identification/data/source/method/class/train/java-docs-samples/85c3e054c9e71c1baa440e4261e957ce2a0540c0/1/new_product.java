@BeforeClass
  public static void beforeClass() throws IOException, InterruptedException {
    if (!LocalGcdHelper.isActive(PROJECT_ID, PORT)) {
      gcdHelper = LocalGcdHelper.start(PROJECT_ID, PORT, 1.0);
    }
  }