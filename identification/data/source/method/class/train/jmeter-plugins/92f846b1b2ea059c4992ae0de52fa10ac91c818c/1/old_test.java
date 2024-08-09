@Test
   public void testSetValue()
   {
      System.out.println("setValue");
      DateTimeRenderer instance = new DateTimeRenderer("HH:mm:ss");

      Calendar test = Calendar.getInstance();

      test.set(Calendar.HOUR_OF_DAY, 3);
      test.set(Calendar.MINUTE, 16);
      test.set(Calendar.SECOND, 40);
      test.set(Calendar.MILLISECOND, 0);
      
      instance.setValue(null);
      assertEquals("", instance.getText());

      instance.setValue(test.getTimeInMillis());
      String text = instance.getText();
      assertEquals("03:16:40", text);
   }