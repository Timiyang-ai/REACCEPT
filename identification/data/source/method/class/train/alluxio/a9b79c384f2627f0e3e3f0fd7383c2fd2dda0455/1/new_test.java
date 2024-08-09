  @Test
  public void getWorker() {
    List<BlockWorkerInfo> workerInfoList = new ArrayList<>();
    workerInfoList.add(new BlockWorkerInfo(new WorkerNetAddress().setHost("worker1")
        .setRpcPort(PORT).setDataPort(PORT).setWebPort(PORT), Constants.GB, 0));
    workerInfoList.add(new BlockWorkerInfo(new WorkerNetAddress().setHost("worker2")
        .setRpcPort(PORT).setDataPort(PORT).setWebPort(PORT), 2 * (long) Constants.GB, 0));
    workerInfoList.add(new BlockWorkerInfo(new WorkerNetAddress().setHost("worker3")
        .setRpcPort(PORT).setDataPort(PORT).setWebPort(PORT), 3 * (long) Constants.GB, 0));
    RoundRobinPolicy policy = new RoundRobinPolicy(ConfigurationTestUtils.defaults());

    GetWorkerOptions options = GetWorkerOptions.defaults().setBlockWorkerInfos(workerInfoList)
        .setBlockInfo(new BlockInfo().setLength(2 * (long) Constants.GB));
    assertNotEquals(
        policy.getWorker(options).getHost(),
        policy.getWorker(options.setBlockInfo(options.getBlockInfo().setBlockId(123))).getHost());

    assertEquals(
        policy.getWorker(options.setBlockInfo(options.getBlockInfo().setBlockId(555))).getHost(),
        policy.getWorker(options.setBlockInfo(options.getBlockInfo().setBlockId(555))).getHost());
  }