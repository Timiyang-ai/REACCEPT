@Test(description = "PUT /vApp/{id}", dependsOnMethods = { "testGetVApp" })
   public void testEditVApp() {
      VApp newVApp = VApp.builder().name(name("new-name-")).description("New Description").build();
      vAppNames.add(newVApp.getName());

      // The method under test
      Task editVApp = vAppApi.edit(vAppUrn, newVApp);
      assertTrue(retryTaskSuccess.apply(editVApp), String.format(TASK_COMPLETE_TIMELY, "editVApp"));

      // Get the edited VApp
      vApp = vAppApi.get(vAppUrn);

      // Check the required fields are set
      assertEquals(vApp.getName(), newVApp.getName(),
               String.format(OBJ_FIELD_EQ, VAPP, "Name", newVApp.getName(), vApp.getName()));
      assertEquals(vApp.getDescription(), newVApp.getDescription(),
               String.format(OBJ_FIELD_EQ, VAPP, "Description", newVApp.getDescription(), vApp.getDescription()));
   }