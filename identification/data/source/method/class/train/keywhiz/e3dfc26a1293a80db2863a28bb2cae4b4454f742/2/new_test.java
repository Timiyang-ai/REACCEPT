  @Test public void secretDetailForGroup() throws Exception {
    // Sample group
    create(CreateGroupRequestV2.builder().name("groupWithSecrets").description("desc").build());

    // Sample client
    createSecret("groupWithSecrets", "test-secret");

    Set<SanitizedSecret> secrets = secretsInfo("groupWithSecrets");

    assertThat(secrets).hasSize(1);
    assertThat(secrets.iterator().next().name()).isEqualTo("test-secret");
  }