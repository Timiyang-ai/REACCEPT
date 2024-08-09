  @Test
  public void getServers_empty() {
    ServerList servers = channelz.getServers(/*fromId=*/ 0, /*maxPageSize=*/ 1);
    assertTrue(servers.end);
    assertThat(servers.servers).isEmpty();
  }