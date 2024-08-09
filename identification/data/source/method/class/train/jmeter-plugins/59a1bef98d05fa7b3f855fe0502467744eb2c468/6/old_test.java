@Test
   public void testGetThreadGroupDelay()
   {
      System.out.println("getThreadGroupDelay");
      SteppingThreadGroup instance = new SteppingThreadGroup();
      int expResult = 0;
      int result = instance.getThreadGroupDelay();
      assertEquals(expResult, result);
   }