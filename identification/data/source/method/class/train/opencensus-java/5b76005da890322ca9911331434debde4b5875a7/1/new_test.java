  @Test
  public void toResourceProto_Null() {
    Resource resourceProto = OcAgentNodeUtils.toResourceProto(null);
    assertThat(resourceProto).isNull();
  }