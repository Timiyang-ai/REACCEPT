  @Test
  public void getWorkerInfoList() {
    long worker1 = mBlockMaster.getWorkerId(NET_ADDRESS_1);
    long worker2 = mBlockMaster.getWorkerId(NET_ADDRESS_2);
    Set<Long> expected = new HashSet<>();
    expected.add(worker1);
    expected.add(worker2);
    Response response = mHandler.getWorkerInfoList();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertTrue("Entry must be a List!", (response.getEntity() instanceof List));
      @SuppressWarnings("unchecked")
      List<WorkerInfo> entry = (List<WorkerInfo>) response.getEntity();
      Set<Long> actual = new HashSet<>();
      for (WorkerInfo info : entry) {
        actual.add(info.getId());
      }
      assertEquals(expected, actual);
    } finally {
      response.close();
    }
  }