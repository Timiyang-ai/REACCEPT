  @Test public void groupListing() throws Exception {
    Set<String> groupsBefore = listing();
    Set<String> expected = ImmutableSet.<String>builder()
        .addAll(groupsBefore)
        .add("group5")
        .build();

    create(CreateGroupRequestV2.builder().name("group5").build());
    assertThat(listing()).containsAll(expected);
  }