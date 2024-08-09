@Test(description = "GET /v${apiVersion}/{tenantId}/flavors/detail")
   public void testListFlavorsInDetail() throws Exception {
      for (String zoneId : zones) {
         FlavorApi api = novaContext.getApi().getFlavorApiForZone(zoneId);
         Set<? extends Flavor> response = api.listInDetail().concat().toImmutableSet();
         assertNotNull(response);
         assertFalse(response.isEmpty());
         for (Flavor flavor : response) {
             assertNotNull(flavor.getId());
             assertNotNull(flavor.getName());
             assertNotNull(flavor.getLinks());
             assertTrue(flavor.getRam() > 0);
             assertTrue(flavor.getDisk() > 0);
             assertTrue(flavor.getVcpus() > 0);
         }
      }
   }