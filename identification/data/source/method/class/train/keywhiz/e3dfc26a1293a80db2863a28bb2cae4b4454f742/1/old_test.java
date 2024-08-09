  @Test public void clientDetailForGroup() throws Exception {
    // Sample group
    create(CreateGroupRequestV2.builder().name("groupWithClients").description("desc").build());

    // Sample client
    createClient("test-client", "groupWithClients");

    Set<Client> clients = clientsInfo("groupWithClients");

    assertThat(clients).hasSize(1);
    assertThat(clients.iterator().next().getName()).isEqualTo("test-client");
  }