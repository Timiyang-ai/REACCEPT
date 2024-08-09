@Test
   public void testListFlavors() throws Exception {
      for (String zoneId : novaContext.getApi().getConfiguredZones()) {
         FlavorApi api = novaContext.getApi().getFlavorApiForZone(zoneId);
         Set<? extends Resource> response = api.listFlavors();
         assert null != response;
         assertTrue(response.size() >= 0);
         for (Resource flavor : response) {
            Flavor newDetails = api.getFlavor(flavor.getId());
            assertEquals(newDetails.getId(), flavor.getId());
            assertEquals(newDetails.getName(), flavor.getName());
            assertEquals(newDetails.getLinks(), flavor.getLinks());
         }
      }
   }