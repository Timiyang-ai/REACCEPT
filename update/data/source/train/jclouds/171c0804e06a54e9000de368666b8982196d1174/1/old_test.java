@Test(enabled=false)
   public void testCloneServer() throws Exception {
      ServerDetails testServer2 = api.cloneServer(serverId, testHostName2, CloneServerOptions.Builder.cpucores(1));

      assertNotNull(testServer2.getId());
      assertEquals(testServer2.getHostname(), "jclouds-test2");
      assertTrue(testServer2.getIps().isEmpty());
      
      testServerId2 = testServer2.getId();

      RetryablePredicate<Server.State> cloneChecker = new ServerStatusChecker(api, testServerId2, 300, 10, TimeUnit.SECONDS);
      assertTrue(cloneChecker.apply(Server.State.STOPPED));

      api.startServer(testServer2.getId());

      // TODO ServerStatus==STOPPED suggests the previous call to start should have worked
      cloneChecker = new RetryablePredicate<Server.State>(
            new Predicate<Server.State>() {

               public boolean apply(Server.State value) {
                  ServerStatus status = api.getServerStatus(testServerId2, ServerStatusOptions.Builder.state());
                  if (status.getState() == value) {
                     return true;
                  }

                  api.startServer(testServerId2);
                  return false;
               }

            }, 600, 30, TimeUnit.SECONDS);

      assertTrue(cloneChecker.apply(Server.State.RUNNING));
   }