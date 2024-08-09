@Test(description = "PUT /vApp/{id}/networkConnectionSection", dependsOnMethods = { "testEditGuestCustomizationSection" })
   public void testEditNetworkConnectionSection() {
      powerOffVm(vm.getHref());
      // Look up a network in the Vdc
      Set<Reference> networks = vdc.getAvailableNetworks();
      Reference network = Iterables.getLast(networks);

      // Copy existing section and edit fields
      NetworkConnectionSection oldSection = vmApi.getNetworkConnectionSection(vm.getHref());
      NetworkConnectionSection newSection = oldSection
               .toBuilder()
               .networkConnection(
                        NetworkConnection.builder().ipAddressAllocationMode(IpAddressAllocationMode.DHCP.toString())
                                 .network(network.getName()).build()).build();

      // The method under test
      Task editNetworkConnectionSection = vmApi.editNetworkConnectionSection(vm.getHref(), newSection);
      assertTrue(retryTaskSuccess.apply(editNetworkConnectionSection),
               String.format(TASK_COMPLETE_TIMELY, "editNetworkConnectionSection"));

      // Retrieve the modified section
      NetworkConnectionSection modified = vmApi.getNetworkConnectionSection(vm.getHref());

      // Check the retrieved object is well formed
      checkNetworkConnectionSection(modified);

      // Check the modified section has an extra network connection
      assertEquals(modified.getNetworkConnections().size(), newSection.getNetworkConnections().size() + 1);

      // Check the section was modified correctly
      assertEquals(modified, newSection, String.format(ENTITY_EQUAL, "NetworkConnectionSection"));
   }