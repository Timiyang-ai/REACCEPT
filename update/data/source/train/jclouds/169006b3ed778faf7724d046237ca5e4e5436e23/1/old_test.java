@Test(description = "GET /vApp/{id}")
   public void testGetVApp() {
      // The method under test
      vApp = vAppApi.getVApp(vAppURI);

      // Check the retrieved object is well formed
      checkVApp(vApp);

      // Check the required fields are set
      assertEquals(vApp.isDeployed(), Boolean.FALSE,
               String.format(OBJ_FIELD_EQ, VAPP, "deployed", "FALSE", vApp.isDeployed().toString()));
      assertTrue(vApp.getName().startsWith("test-vapp-"),
               String.format(MATCHES_STRING_FMT, "name", "test-vapp-*", vApp.getName()));
      assertEquals(vApp.getDescription(), "Test VApp",
               String.format(OBJ_FIELD_EQ, VAPP, "Description", "Test VApp", vApp.getDescription()));

      // TODO instantiationParams instantiationParams()
      // TODO source.href vAppTemplateURI

      // Check status
      assertVAppStatus(vAppURI, Status.POWERED_OFF);
   }