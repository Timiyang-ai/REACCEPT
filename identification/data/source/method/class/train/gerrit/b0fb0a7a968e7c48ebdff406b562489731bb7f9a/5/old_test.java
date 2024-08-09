  @Test
  public void append_boolean() {
    final SafeHtmlBuilder b = new SafeHtmlBuilder();
    assertThat(b).isSameAs(b.append(true));
    assertThat(b).isSameAs(b.append(false));
    assertThat(b.asString()).isEqualTo("truefalse");
  }