@Test(testName = "PUT /vApp/{id}/networkConnectionSection", dependsOnMethods = { "testGetNetworkConnectionSection" })
   public void testModifyNetworkConnectionSection() {
      // Get URI for child VM
      URI vmURI = Iterables.getOnlyElement(vApp.getChildren().getVms()).getHref();

      // Copy existing section and update fields
      NetworkConnectionSection oldSection = vAppClient.getNetworkConnectionSection(vmURI);
      NetworkConnectionSection newSection = oldSection.toBuilder()
            .info("Changed NetworkConnectionSection Info")
            .build();

      // The method under test
      Task modifyNetworkConnectionSection = vAppClient.modifyNetworkConnectionSection(vmURI, newSection);
      assertTrue(retryTaskSuccess.apply(modifyNetworkConnectionSection), String.format(TASK_COMPLETE_TIMELY, "modifyNetworkConnectionSection"));

      // Retrieve the modified section
      NetworkConnectionSection modified = vAppClient.getNetworkConnectionSection(vmURI);

      // Check the retrieved object is well formed
      checkNetworkConnectionSection(modified);

      // Check the modified section fields are set correctly
      assertEquals(modified.getInfo(), newSection.getInfo());

      // Check the section was modified correctly
      assertEquals(modified, newSection, String.format(ENTITY_EQUAL, "NetworkConnectionSection"));
   }