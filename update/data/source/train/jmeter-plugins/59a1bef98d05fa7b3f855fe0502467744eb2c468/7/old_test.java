@Test
   public void testGetOutUserPeriod()
   {
      System.out.println("getOutUserPeriod");
      SteppingThreadGroup instance = new SteppingThreadGroup();
      int expResult = 0;
      int result = instance.getOutUserPeriod();
      assertEquals(expResult, result);
   }