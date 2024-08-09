@Test(description = "PUT /admin/vdc/{id}/metadata", enabled=false)
   public void testSetMetadata() throws Exception {
      metadataKey = name("key-");
      metadataValue = name("value-");
      Metadata metadata = Metadata.builder()
               .entry(MetadataEntry.builder().entry(metadataKey, metadataValue).build())
               .build();
      
      Task task = metadataApi.mergeMetadata(adminVdcUri, metadata);
      assertTaskSucceeds(task);
      
      MetadataValue modified = metadataApi.getMetadataValue(adminVdcUri, metadataKey);
      Checks.checkMetadataValueFor("AdminVdc", modified, metadataValue);
      Checks.checkMetadata(metadata);
   }