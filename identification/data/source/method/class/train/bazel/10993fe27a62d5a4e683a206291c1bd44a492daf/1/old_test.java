  @Test
  public void add() {
    IterablesChain.Builder<String> builder = IterablesChain.builder();
    builder.add(ImmutableList.of("a", "b"));
    assertThat(builder.build()).containsExactly("a", "b").inOrder();
  }