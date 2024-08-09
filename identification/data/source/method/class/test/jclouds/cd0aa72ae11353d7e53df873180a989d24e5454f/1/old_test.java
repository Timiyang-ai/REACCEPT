@Test(description = "PUT /vApp/{id}", dependsOnMethods = { "testGetVApp" })
   public void testModifyVApp() {
      VApp newVApp = VApp.builder()
            .name(name("new-name-"))
            .description("New Description")
            .build();
      vAppNames.add(newVApp.getName());

      // The method under test
      Task modifyVApp = vAppApi.modifyVApp(vApp.getHref(), newVApp);
      assertTrue(retryTaskSuccess.apply(modifyVApp), String.format(TASK_COMPLETE_TIMELY, "modifyVApp"));

      // Get the updated VApp
      vApp = vAppApi.getVApp(vApp.getHref());

      // Check the required fields are set
      assertEquals(vApp.getName(), newVApp.getName(), String.format(OBJ_FIELD_EQ, VAPP, "Name", newVApp.getName(), vApp.getName()));
      assertEquals(vApp.getDescription(), newVApp.getDescription(), String.format(OBJ_FIELD_EQ, VAPP, "Description", newVApp.getDescription(), vApp.getDescription()));
   }