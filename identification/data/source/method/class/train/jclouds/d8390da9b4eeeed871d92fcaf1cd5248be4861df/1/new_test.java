@Test(testName = "GET /catalog/{id}/metadata/{key}", dependsOnMethods = { "testGetCatalogMetadata" }, enabled = false)
   public void testGetCatalogMetadataValue() {
      MetadataEntry existingMetadataEntry = Iterables.find(catalogMetadata.getMetadataEntries(), new Predicate<MetadataEntry>() {
         @Override
         public boolean apply(MetadataEntry input) {
            return input.getKey().equals("KEY");
         }
      });
      MetadataValue metadataValue = catalogClient.getCatalogMetadataValue(catalogRef, "KEY");
      // XXX
      assertEquals(metadataValue.getValue(), existingMetadataEntry.getValue(),
            "The MetadataValue for KEY should have the same Value as the existing MetadataEntry");
      checkMetadataValue(metadataValue);
   }