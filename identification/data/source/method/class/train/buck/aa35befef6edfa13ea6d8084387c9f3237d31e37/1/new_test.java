  @Test
  public void concat() {
    List<String> result =
        RichStream.of("a", "b").concat(Stream.of("c", "d")).collect(Collectors.toList());
    Assert.assertEquals(ImmutableList.of("a", "b", "c", "d"), result);
  }