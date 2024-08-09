  @Test public void parseParentId() {
    String parentIdString = "48485a3953bb6124";

    TraceContext.Builder builder = parseGoodParentId(parentIdString);

    assertThat(HexCodec.toLowerHex(builder.parentId))
      .isEqualTo(parentIdString);
  }