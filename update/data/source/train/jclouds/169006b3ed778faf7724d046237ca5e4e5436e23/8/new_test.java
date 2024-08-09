@Test(description = "POST /vApp/{id}/action/relocate", dependsOnMethods = { "testGetVm" })
   public void testRelocate() {
      // Relocate to the last of the available datastores
      QueryResultRecords records = context.getApi().getQueryApi().queryAll("datastore");
      QueryResultRecordType datastore = Iterables.getLast(records.getRecords());
      RelocateParams params = RelocateParams.builder().datastore(Reference.builder().href(datastore.getHref()).build())
               .build();

      // The method under test
      Task relocate = vmApi.relocate(vmUrn, params);
      assertTrue(retryTaskSuccess.apply(relocate), String.format(TASK_COMPLETE_TIMELY, "relocate"));
   }