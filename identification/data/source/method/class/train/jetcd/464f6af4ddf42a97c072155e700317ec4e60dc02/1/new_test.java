@Test(dependsOnMethods = "testAddMember")
  public void testUpdateMember() {

    Throwable throwable = null;
    try {
      Client client = Client.builder()
          .endpoints(endpoints.subList(1, 3))
          .build();

      Cluster clusterClient = client.getClusterClient();
      MemberListResponse response = clusterClient.listMember().get();
      List<URI> newPeerUrl = peerUrls.subList(0, 1);
      clusterClient.updateMember(response.getMembers().get(0).getId(), newPeerUrl)
          .get();
    } catch (Exception e) {
      System.out.println(e);
      throwable = e;
    }
    assertion.assertNull(throwable, "update for member");
  }