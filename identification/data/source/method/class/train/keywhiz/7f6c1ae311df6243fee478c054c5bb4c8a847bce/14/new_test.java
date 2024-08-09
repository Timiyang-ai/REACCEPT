  @Test public void createGroup_success() throws Exception {
    Response httpResponse = create(CreateGroupRequestV2.builder().name("group1").build());
    assertThat(httpResponse.code()).isEqualTo(201);
    URI location = URI.create(httpResponse.header(LOCATION));
    assertThat(location.getPath()).isEqualTo("/automation/v2/groups/group1");
  }