  @Test public void deleteGroup_success() throws Exception {
    create(CreateGroupRequestV2.builder().name("group6").build());

    Response httpResponse = delete("group6");
    assertThat(httpResponse.code()).isEqualTo(204);
  }