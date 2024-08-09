@Test(description = "PUT /vAppTemplate/{id}/leaseSettingsSection")
   public void testEditLeaseSettingsSection() throws Exception {
      // NOTE use smallish number for storageLeaseInSeconds; it seems to be capped at 5184000?
      int storageLeaseInSeconds = random.nextInt(10000)+1;

      LeaseSettingsSection leaseSettingSection = LeaseSettingsSection.builder()
               .info("my info")
               .storageLeaseInSeconds(storageLeaseInSeconds)
               .build();
      
      final Task task = vAppTemplateApi.editLeaseSettingsSection(vAppTemplateURI, leaseSettingSection);
      assertTaskSucceeds(task);
      
      LeaseSettingsSection newLeaseSettingsSection = vAppTemplateApi.getLeaseSettingsSection(vAppTemplateURI);
      assertEquals(newLeaseSettingsSection.getStorageLeaseInSeconds(), (Integer) storageLeaseInSeconds);
   }