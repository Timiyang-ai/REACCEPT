@Test(enabled=false)
   public void testCloneServer() throws Exception {
      ServerDetails testServer2 = client.cloneServer(testServerId, testHostName2, ServerCloneOptions.Builder.cpucores(1));

      assertNotNull(testServer2.getId());
      assertEquals(testServer2.getHostname(), "jclouds-test2");
      assertTrue(testServer2.getIps().isEmpty());
      
      testServerId2 = testServer2.getId();

      RetryablePredicate<ServerState> cloneChecker = new ServerStatusChecker(client, testServerId2, 300, 10, TimeUnit.SECONDS);
      assertTrue(cloneChecker.apply(ServerState.STOPPED));

      client.startServer(testServer2.getId());

      // TODO ServerStatus==STOPPED suggests the previous call to start should have worked
      cloneChecker = new RetryablePredicate<ServerState>(
            new Predicate<ServerState>() {

               public boolean apply(ServerState value) {
                  ServerStatus status = client.getServerStatus(testServerId2, ServerStatusOptions.Builder.state());
                  if (status.getState() == value) {
                     return true;
                  }

                  client.startServer(testServerId2);
                  return false;
               }

            }, 300, 10, TimeUnit.SECONDS);

      assertTrue(cloneChecker.apply(ServerState.RUNNING)

      );
   }