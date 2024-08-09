@Test
   public void testGetMem()
   {
      System.out.println("getMem");
      prepareTestData(123);
      long result = instance.getMem();
      System.out.println(result);
      assertTrue(result >= 0);
   }