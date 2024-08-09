@Test
   public void testTestStarted()
   {
      System.out.println("testStarted");

      ServerPerfMonitoringGUI instance = new ServerPerfMonitoringGUI();
      instance.configure(testElementWithConnectors);
      TestSocketFactory sf = new TestSocketFactory();
      instance.setSocketFactory(sf);
      instance.testStarted();
      instance.testEnded();
   }