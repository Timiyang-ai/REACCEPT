@Test(testName = "GET /vApp/{id}")
   public void testGetVApp() {
      VApp vAppInstantiated = instantiateVApp();

      // The method under test
      vApp = vAppClient.getVApp(vAppInstantiated.getHref());

      // Check the retrieved object is well formed
      checkVApp(vApp);

      assertEquals(vApp, vAppInstantiated, String.format(ENTITY_EQUAL, VAPP));
   }