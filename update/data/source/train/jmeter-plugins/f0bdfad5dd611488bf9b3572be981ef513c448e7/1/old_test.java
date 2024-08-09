@Test
   public void testSetCmdRegExps() {
      System.out.println("setCmdRegExps");
      HashMap<String, String> cmdRegExps = new HashMap<String, String>();
      cmdRegExps.put("regKey", "regVal");
      PageDataExtractorOverTimeGui instance = new PageDataExtractorOverTimeGui();
      instance.setCmdRegExps(cmdRegExps);
   }