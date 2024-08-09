@Test
   public void testSetCmdRegExps() {
      System.out.println("setCmdRegExps");
      ArrayList<Object> data = new ArrayList<Object>();
      data.add("regKey");
      data.add("regVal");
      data.add(true);
      PageDataExtractorOverTimeGui instance = new PageDataExtractorOverTimeGui();
      instance.setCmdRegExps(data);
   }