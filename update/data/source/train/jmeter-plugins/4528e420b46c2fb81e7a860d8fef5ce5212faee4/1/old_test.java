@Test
   public void testIterator()
   {
      System.out.println("iterator");
      AbstractGraphRow instance = new AbstractGraphRowImpl();
      Iterator expResult = null;
      Iterator result = instance.iterator();
      assertEquals(expResult, result);
   }