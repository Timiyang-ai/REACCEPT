@Test(description = "DELETE /catalogItem/{id}", dependsOnMethods = "testDeleteCatalogItemMetadataValue")
   public void testDeleteCatalogItem() {
      catalogApi.deleteCatalogItem(catalogItem.getHref());
      catalogItem = catalogApi.getCatalogItem(catalogItem.getHref());
      assertNull(catalogItem);
   }