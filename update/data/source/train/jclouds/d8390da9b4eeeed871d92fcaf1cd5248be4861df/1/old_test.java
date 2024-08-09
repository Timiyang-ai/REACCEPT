@Test(testName = "GET /catalog/{id}/metadata/{key}", dependsOnMethods = { "testGetCatalogMetadata" }, enabled = false)
   public void testGetCatalogMetadataEntry() {
      MetadataEntry existingMetadataEntry = Iterables.find(catalogMetadata.getMetadataEntries(), new Predicate<MetadataEntry>() {
         @Override
         public boolean apply(MetadataEntry input) {
            return input.getKey().equals("KEY");
         }
      });
      MetadataEntry metadataEntry = catalogClient.getCatalogMetadataEntry(catalogRef, "KEY");
      assertEquals(existingMetadataEntry.getValue(), metadataEntry.getValue());
      checkMetadataEntry(metadataEntry);
   }