@Test(description = "GET /v${apiVersion}/{tenantId}/extensions/{alias}", dependsOnMethods = { "testListExtensions" })
    public void testGetExtensionByAlias() throws Exception {
       for (String zoneId : zones) {
           ExtensionApi api = novaContext.getApi().getExtensionApiForZone(zoneId);
           Set<? extends Extension> response = api.listExtensions();
           for (Extension extension : response) {
              Extension details = api.getExtensionByAlias(extension.getId());
              assertNotNull(details);
              assertEquals(details.getId(), extension.getId());
              assertEquals(details.getName(), extension.getName());
              assertEquals(details.getDescription(), extension.getDescription());
              assertEquals(details.getNamespace(), extension.getNamespace());
              assertEquals(details.getUpdated(), extension.getUpdated());
              assertEquals(details.getLinks(), extension.getLinks());
           }
        }
    }