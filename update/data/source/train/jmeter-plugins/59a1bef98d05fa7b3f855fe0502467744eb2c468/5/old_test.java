@Test
   public void testGetOutUserCount()
   {
      System.out.println("getOutUserCount");
      SteppingThreadGroup instance = new SteppingThreadGroup();
      int expResult = 1;
      int result = instance.getOutUserCount();
      assertEquals(expResult, result);
   }