  @Before
  public void init() {
    Args.setParam(new String[]{"-d", dbPath, "-w"}, Constant.TEST_CONF);
    Args.getInstance().setNodeListenPort(10000 + port.incrementAndGet());
    context = new TronApplicationContext(DefaultConfig.class);

    dbManager = context.getBean(Manager.class);
    setManager(dbManager);
    dposSlot = context.getBean(DposSlot.class);
    consensusService = context.getBean(ConsensusService.class);
    consensusService.start();
    blockCapsule2 =
        new BlockCapsule(
            1,
            Sha256Hash.wrap(ByteString.copyFrom(
                ByteArray.fromHexString(
                    "0304f784e4e7bae517bcab94c3e0c9214fb4ac7ff9d7d5a937d1f40031f87b81"))),
            0,
            ByteString.copyFrom(
                ECKey.fromPrivate(
                    ByteArray.fromHexString(
                        Args.getInstance().getLocalWitnesses().getPrivateKey()))
                    .getAddress()));
    blockCapsule2.setMerkleRoot();
    blockCapsule2.sign(
        ByteArray.fromHexString(Args.getInstance().getLocalWitnesses().getPrivateKey()));
  }