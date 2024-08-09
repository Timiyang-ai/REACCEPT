@Test(testName = "PUT /vApp/{id}/media/action/insertMedia", dependsOnMethods = { "testPowerOnVApp" })
   public void testInsertMedia() {
      // Setup media params from configured media id
      MediaInsertOrEjectParams params = MediaInsertOrEjectParams.builder()
            .media(Reference.builder().href(mediaURI).type(MEDIA).build())
            .build();

      // Get URI for child VM
      URI vmURI = Iterables.getOnlyElement(vApp.getChildren().getVms()).getHref();

      // The method under test
      Task insertMedia = vAppClient.insertMedia(vmURI, params);
      assertTrue(retryTaskSuccess.apply(insertMedia), String.format(TASK_COMPLETE_TIMELY, "insertMedia"));
   }