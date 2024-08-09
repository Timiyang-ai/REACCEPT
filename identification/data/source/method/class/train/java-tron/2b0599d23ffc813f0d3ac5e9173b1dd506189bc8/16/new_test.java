  @BeforeClass
  public static void init() {
    dbManager = context.getBean(Manager.class);
    //init energy
    dbManager.getDynamicPropertiesStore().saveLatestBlockHeaderTimestamp(1526647838000L);
    dbManager.getDynamicPropertiesStore().saveTotalEnergyWeight(100_000L);
    dbManager.getDynamicPropertiesStore().saveLatestBlockHeaderTimestamp(0);
    VMConfig.initVmHardFork(false);

  }