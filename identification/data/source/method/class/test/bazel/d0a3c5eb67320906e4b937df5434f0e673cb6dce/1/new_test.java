@Test
  public void testVisitLabels() throws Exception {
    RawAttributeMapper rawMapper = RawAttributeMapper.of(setupGenRule());
    try {
      rawMapper.visitLabels();
      fail("Expected label visitation to fail since one attribute is configurable");
    } catch (IllegalArgumentException e) {
      assertThat(e)
          .hasCauseThat()
          .hasMessageThat()
          .containsMatch(".*SelectorList cannot be cast to .*java\\.util\\.List");
    }
  }