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

    Assert.assertNotEquals(
        policy.getWorkerForNextBlock(workerInfoList, 2 * (long) Constants.GB).getHost(),
        policy.getWorkerForNextBlock(workerInfoList, 2 * (long) Constants.GB).getHost());
  }