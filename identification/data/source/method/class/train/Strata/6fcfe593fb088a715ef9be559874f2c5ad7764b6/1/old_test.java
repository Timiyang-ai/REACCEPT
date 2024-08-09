  @Test
  public void test_empty() {
    Attributes test = Attributes.empty();
    assertThat(test.findAttribute(AttributeType.DESCRIPTION)).isEmpty();
    assertThat(test.containsAttribute(AttributeType.DESCRIPTION)).isFalse();
    assertThatIllegalArgumentException().isThrownBy(() -> test.getAttribute(AttributeType.DESCRIPTION));

    Attributes test2 = test.withAttribute(AttributeType.NAME, "world");
    assertThat(test2.getAttribute(AttributeType.NAME)).isEqualTo("world");
  }