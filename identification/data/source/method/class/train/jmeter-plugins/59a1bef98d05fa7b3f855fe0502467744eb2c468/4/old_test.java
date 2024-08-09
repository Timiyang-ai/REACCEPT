@Test
   public void testGetInUserCount()
   {
      System.out.println("getInUserCount");
      SteppingThreadGroup instance = new SteppingThreadGroup();
      int expResult = 1;
      int result = instance.getInUserCount();
      assertEquals(expResult, result);
   }