@Test
   public void testListExtensions() throws Exception {
      for (String zoneId : novaContext.getApi().getConfiguredZones()) {
         ExtensionApi api = novaContext.getApi().getExtensionApiForZone(zoneId);
         Set<? extends Extension> response = api.listExtensions();
         assert null != response;
         assertTrue(response.size() >= 0);
         for (Extension extension : response) {
            Extension newDetails = api.getExtensionByAlias(extension.getId());
            assertEquals(newDetails.getId(), extension.getId());
            assertEquals(newDetails.getName(), extension.getName());
            assertEquals(newDetails.getDescription(), extension.getDescription());
            assertEquals(newDetails.getNamespace(), extension.getNamespace());
            assertEquals(newDetails.getUpdated(), extension.getUpdated());
            assertEquals(newDetails.getLinks(), extension.getLinks());
         }
      }
   }