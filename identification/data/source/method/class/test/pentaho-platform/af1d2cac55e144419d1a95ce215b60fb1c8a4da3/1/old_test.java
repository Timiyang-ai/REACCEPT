@Test
  public void testAddBlockout() {
    BlockoutTrigger trigger = new BlockoutTrigger("blockout", DefaultBlockoutManager.BLOCK_GROUP, "job_name", DefaultBlockoutManager.BLOCK_GROUP, new Date(), null, -1, 1000000, 50000 );
    try {
      blockoutManager.addBlockout(trigger);
    } catch (SchedulerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      assertEquals(blockoutManager.getBlockout("blockout"), trigger);
    } catch (SchedulerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }