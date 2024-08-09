@Test(description = "DELETE /catalogItem/{id}", dependsOnMethods = "testDeleteCatalogItemMetadataValue")
   public void testDeleteCatalogItem() {
      catalogApi.deleteItem(catalogItem.getId());
      catalogItem = catalogApi.getItem(catalogItem.getId());
      assertNull(catalogItem);
   }