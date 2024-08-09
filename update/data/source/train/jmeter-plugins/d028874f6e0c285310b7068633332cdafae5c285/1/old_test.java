@Test
   public void testAdd()
   {
      System.out.println("add");
      long xVal = 10;
      double yVal = 5.0;
      instance.add(xVal, yVal);
      assertEquals(xVal, instance.getMinX());
      assertEquals(xVal, instance.getMaxX());
      assertEquals(yVal, instance.getMinY(), 0.001);
      assertEquals(yVal, instance.getMaxY(), 0.001);
   }