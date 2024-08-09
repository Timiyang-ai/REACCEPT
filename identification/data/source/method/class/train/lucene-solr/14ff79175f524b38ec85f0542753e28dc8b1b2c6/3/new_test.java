  @BeforeClass
  public static void init() throws Exception {
    TEST_MBEAN_SERVER = MBeanServerFactory.createMBeanServer();
    PREFIX = getSimpleClassName() + "-";
  }