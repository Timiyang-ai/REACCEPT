@Test(description = "GET /v${apiVersion}/{tenantId}/extensions")
    public void testListExtensions() throws Exception {
       for (String zoneId : zones) {
          ExtensionApi api = novaContext.getApi().getExtensionApiForZone(zoneId);
          Set<? extends Extension> response = api.listExtensions();
          assertNotNull(response);
          assertFalse(response.isEmpty());
           for (Extension extension : response) {
              assertNotNull(extension.getId());
              assertNotNull(extension.getName());
              assertNotNull(extension.getDescription());
              assertNotNull(extension.getNamespace());
              assertNotNull(extension.getUpdated());
              assertNotNull(extension.getLinks());
           }
       }
    }