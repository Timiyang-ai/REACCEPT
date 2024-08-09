@Test(description = "GET /admin/vdc/{id}/metadata/{key}", dependsOnMethods = { "testSetMetadata" }, enabled=false)
   public void testGetMetadataValue() throws Exception {
      MetadataValue retrievedMetadataValue = metadataApi.getValue(adminVdcUri, metadataKey);
         
      Checks.checkMetadataValueFor("AdminVdc", retrievedMetadataValue, metadataValue);
   }