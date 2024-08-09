@Test(dependsOnMethods = "testAddMember")
  public void testUpdateMember() {

    Throwable throwable = null;
    try {
      Client client = Client.builder()
          .endpoints(endpoints.subList(1, 3))
          .build();

      Cluster clusterClient = client.getClusterClient();
      MemberListResponse response = clusterClient.listMember().get();
      String[] newPeerUrl = peerUrls.subList(0, 1).toArray(new String[]{});
      clusterClient.updateMember(response.getMembers().get(0).getId(), Arrays.asList(newPeerUrl))
          .get();
    } catch (Exception e) {
      System.out.println(e);
      throwable = e;
    }
    assertion.assertNull(throwable, "update for member");
  }