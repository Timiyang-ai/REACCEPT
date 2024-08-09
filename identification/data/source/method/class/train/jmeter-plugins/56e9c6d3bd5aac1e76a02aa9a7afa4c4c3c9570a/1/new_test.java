@Test
   public void testGetCpu()
   {
      System.out.println("getCpu");
      prepareTestData(123);
      double result = instance.getCpu();
      System.out.println(result);
      assertTrue(result >= 0);
   }