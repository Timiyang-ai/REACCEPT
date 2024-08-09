@Test(testName = "POST /catalog/{id}/catalogItems", dependsOnMethods = { "testGetCatalog" }, enabled = false)
   public void testAddCatalogItem() {
      CatalogItem editedCatalogItem = CatalogItem.builder()
            .name("newitem")
            .description("New Item")
            // XXX org.jclouds.vcloud.director.v1_5.VCloudDirectorException: Error: The VCD entity image already exists.
            // .entity(Reference.builder().href(catalogItem.getEntity().getHref()).build())
            // XXX org.jclouds.vcloud.director.v1_5.VCloudDirectorException: Error: The VCD entity ubuntu10 already exists.
            // .entity(Reference.builder().href(URI.create(endpoint + "/vAppTemplate/vappTemplate-ef4415e6-d413-4cbb-9262-f9bbec5f2ea9")).build())
            .build();
      newCatalogItem = catalogClient.addCatalogItem(catalogRef, editedCatalogItem);
      checkCatalogItem(newCatalogItem);
      assertEquals(newCatalogItem.getName(), "newitem");
   }