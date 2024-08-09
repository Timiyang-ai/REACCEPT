@Test
   public void testGetFlightTime()
   {
      System.out.println("getFlightTime");
      SteppingThreadGroup instance = new SteppingThreadGroup();
      int expResult = 0;
      int result = instance.getFlightTime();
      assertEquals(expResult, result);
   }