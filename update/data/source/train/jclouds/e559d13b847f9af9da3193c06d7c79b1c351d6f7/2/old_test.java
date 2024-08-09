@Test(testName = "PUT /vApp/{id}", dependsOnMethods = { "testGetVApp" })
   public void testModifyVApp() {
      vApp.setName("new-name");
      vApp.setDescription("New Description");

      // The method under test
      Task modifyVApp = vAppClient.modifyVApp(vApp.getHref(), vApp);
      assertTrue(retryTaskSuccess.apply(modifyVApp), String.format(TASK_COMPLETE_TIMELY, "modifyVApp"));

      vApp = vAppClient.getVApp(vApp.getHref());
      assertEquals(vApp.getName(), "new-name", String.format(OBJ_FIELD_EQ, VAPP, "name", "new-name", "modified"));
      assertEquals(vApp.getDescription(), "New Description", String.format(OBJ_FIELD_EQ, VAPP, "description", "New Description", "modified"));
   }