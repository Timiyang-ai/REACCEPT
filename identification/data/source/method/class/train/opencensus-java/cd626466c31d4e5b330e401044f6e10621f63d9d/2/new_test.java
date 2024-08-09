  @Test
  public void createLabelDescriptor() {
    assertThat(StackdriverExportUtils.createLabelDescriptor(LabelKey.create("key", "desc")))
        .isEqualTo(
            LabelDescriptor.newBuilder()
                .setKey("key")
                .setDescription("desc")
                .setValueType(ValueType.STRING)
                .build());
  }