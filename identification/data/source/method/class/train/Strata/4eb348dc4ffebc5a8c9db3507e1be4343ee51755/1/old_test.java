  @Test
  public void test_attributes() {
    assertThat(sut2().getAttributeTypes()).containsOnly(AttributeType.NAME);
    assertThat(sut2().getAttribute(AttributeType.NAME)).isEqualTo("NAME");
    assertThat(sut2().findAttribute(AttributeType.NAME)).hasValue("NAME");
    assertThatIllegalArgumentException().isThrownBy(() -> sut2().getAttribute(AttributeType.of("Foo")));
    EtdContractSpec updated = sut2().withAttribute(AttributeType.NAME, "FOO");
    assertThat(updated.getAttribute(AttributeType.NAME)).isEqualTo("FOO");
  }