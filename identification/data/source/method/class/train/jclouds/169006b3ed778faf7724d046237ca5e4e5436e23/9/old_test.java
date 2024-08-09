@Test(description = "PUT /vApp/{id}/media/action/insertMedia", dependsOnMethods = { "testGetVm" })
   public void testInsertMedia() {
      // Setup media params from configured media id
      MediaInsertOrEjectParams params = MediaInsertOrEjectParams.builder()
               .media(Reference.builder().href(mediaURI).type(MEDIA).build()).build();

      // The method under test
      Task insertMedia = vmApi.insertMedia(vm.getHref(), params);
      assertTrue(retryTaskSuccess.apply(insertMedia), String.format(TASK_COMPLETE_TIMELY, "insertMedia"));
   }