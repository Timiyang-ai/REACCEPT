@Test
   public void testAdd()
   {
      System.out.println("add");
      long xVal = 10;
      double yVal = 5.0;
      instance.add(xVal, yVal);

      double[] minMax = instance.getMinMaxY(-1);

      assertEquals(xVal, instance.getMinX());
      assertEquals(xVal, instance.getMaxX());
      assertEquals(yVal, minMax[0], 0.001);
      assertEquals(yVal, minMax[1], 0.001);
   }