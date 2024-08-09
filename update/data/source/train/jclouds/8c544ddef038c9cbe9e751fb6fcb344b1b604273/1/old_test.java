@Test
   public void testListFlavorsInDetail() throws Exception {
      for (String zoneId : novaContext.getApi().getConfiguredZones()) {
         FlavorApi api = novaContext.getApi().getFlavorApiForZone(zoneId);
         Set<Flavor> response = api.listFlavorsInDetail();
         assert null != response;
         assertTrue(response.size() >= 0);
         for (Flavor flavor : response) {
            Flavor newDetails = api.getFlavor(flavor.getId());
            assertEquals(newDetails.getId(), flavor.getId());
            assertEquals(newDetails.getName(), flavor.getName());
            assertEquals(newDetails.getLinks(), flavor.getLinks());
            assertEquals(newDetails.getRam(), flavor.getRam());
            assertEquals(newDetails.getDisk(), flavor.getDisk());
            assertEquals(newDetails.getVcpus(), flavor.getVcpus());
         }
      }

   }