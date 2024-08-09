@Test
  public void testGetBlockout() {
    BlockoutTrigger trigger1 = new BlockoutTrigger("blockout1", DefaultBlockoutManager.BLOCK_GROUP, "job_name", DefaultBlockoutManager.BLOCK_GROUP, new Date(), null, -1, 1000000, 50000 );
    BlockoutTrigger trigger2 = new BlockoutTrigger("blockout2", DefaultBlockoutManager.BLOCK_GROUP, "job_name", DefaultBlockoutManager.BLOCK_GROUP, new Date(), null, -1, 1000000, 50000 );
    try {
      blockoutManager.addBlockout(trigger1);
      blockoutManager.addBlockout(trigger2);
    } catch (SchedulerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      assertEquals(blockoutManager.getBlockout("blockout1"), trigger1);
      assertEquals(blockoutManager.getBlockout("blockout2"), trigger2);
      assertNotSame(trigger1, blockoutManager.getBlockout("blockout2"));
    } catch (SchedulerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }