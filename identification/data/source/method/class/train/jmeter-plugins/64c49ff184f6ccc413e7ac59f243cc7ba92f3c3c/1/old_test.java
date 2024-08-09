@Test
   public void testGetNextColor()
   {
      System.out.println("getNextColor");
      ColorsDispatcher instance = new ColorsDispatcher();
      assertEquals(Color.red, instance.getNextColor());
      assertEquals(Color.green, instance.getNextColor());
      assertEquals(Color.blue, instance.getNextColor());
   }