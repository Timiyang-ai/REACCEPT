@Test
  public void testGetBlockout() {
    SimpleBlockoutTrigger trigger1 = new SimpleBlockoutTrigger("blockout1", new Date(), null, -1, 1000000, 50000 );
    SimpleBlockoutTrigger trigger2 = new SimpleBlockoutTrigger("blockout2", new Date(), null, -1, 1000000, 50000 );
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