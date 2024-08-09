@Test
   public void testIterator()
   {
      System.out.println("iterator");
      AbstractGraphRow instance = new AbstractGraphRowImpl();
      Iterator result = instance.iterator();
      assertNotNull(result);
   }