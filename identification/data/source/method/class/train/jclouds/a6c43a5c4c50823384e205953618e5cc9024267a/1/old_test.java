@Test(enabled = false)
   public void testListAsyncJobs() throws Exception {
      Set<AsyncJob<?>> response = client.getAsyncJobClient().listAsyncJobs();
      assert null != response;
      long asyncJobCount = response.size();
      assertTrue(asyncJobCount >= 0);
      for (AsyncJob<?> asyncJob : response) {
         assert asyncJob.getCmd() != null : asyncJob;
         assert asyncJob.getUserId() >= 0 : asyncJob;
         checkJob(asyncJob);

         AsyncJob<?> query = client.getAsyncJobClient().getAsyncJob(asyncJob.getId());
         assertEquals(query.getId(), asyncJob.getId());

         assert query.getResultType() != null : query;
         checkJob(query);
      }
   }