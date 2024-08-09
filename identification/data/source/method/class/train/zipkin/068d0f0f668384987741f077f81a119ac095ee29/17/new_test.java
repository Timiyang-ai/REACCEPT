  @Test public void parentId_sameAsIdCoerseToNull() {
    assertThat(base.toBuilder().parentId(base.id).build().parentId())
      .isNull();
  }