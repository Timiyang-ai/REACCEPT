@Test
   public void testGetInUserPeriod()
   {
      System.out.println("getInUserPeriod");
      SteppingThreadGroup instance = new SteppingThreadGroup();
      int expResult = 0;
      int result = instance.getInUserPeriod();
      assertEquals(expResult, result);
   }