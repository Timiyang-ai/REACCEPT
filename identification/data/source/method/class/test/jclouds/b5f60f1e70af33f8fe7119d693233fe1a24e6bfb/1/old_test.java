@Test(description = "GET /v${apiVersion}/{tenantId}/flavors")
   public void testListFlavors() throws Exception {
      for (String zoneId : zones) {
         FlavorApi api = novaContext.getApi().getFlavorApiForZone(zoneId);
         Set<? extends Resource> response = api.listFlavors();
         assertNotNull(response);
         assertFalse(response.isEmpty());
         for (Resource flavor : response) {
            assertNotNull(flavor.getId());
            assertNotNull(flavor.getName());
            assertNotNull(flavor.getLinks());
         }
      }
   }