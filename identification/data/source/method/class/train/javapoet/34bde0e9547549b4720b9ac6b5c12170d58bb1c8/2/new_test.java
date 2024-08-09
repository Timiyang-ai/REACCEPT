  @Test public void peerClass() {
    assertThat(ClassName.get(Double.class).peerClass("Short"))
        .isEqualTo(ClassName.get(Short.class));
    assertThat(ClassName.get("", "Double").peerClass("Short"))
        .isEqualTo(ClassName.get("", "Short"));
    assertThat(ClassName.get("a.b", "Combo", "Taco").peerClass("Burrito"))
        .isEqualTo(ClassName.get("a.b", "Combo", "Burrito"));
  }