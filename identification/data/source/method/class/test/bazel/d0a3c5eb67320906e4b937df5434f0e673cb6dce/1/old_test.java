@Test
  public void testVisitLabels() throws Exception {
    RawAttributeMapper rawMapper = RawAttributeMapper.of(setupGenRule());
    try {
      rawMapper.visitLabels(new AttributeMap.AcceptsLabelAttribute() {
        @Override
        public void acceptLabelAttribute(Label label, Attribute attribute) {
          // Nothing to do.
        }
      });
      fail("Expected label visitation to fail since one attribute is configurable");
    } catch (IllegalArgumentException e) {
      assertThat(e)
          .hasCauseThat()
          .hasMessageThat()
          .containsMatch(".*SelectorList cannot be cast to .*java\\.util\\.List");
    }
  }