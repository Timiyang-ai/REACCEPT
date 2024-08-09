  @Test
  public void listHosts() throws Exception {
    final List<String> hosts = ImmutableList.of("foo1", "foo2", "foo3");

    mockResponse("GET", hasPath("/hosts/"), response("GET", 200, hosts));

    assertThat(client.listHosts().get(), equalTo(hosts));
  }