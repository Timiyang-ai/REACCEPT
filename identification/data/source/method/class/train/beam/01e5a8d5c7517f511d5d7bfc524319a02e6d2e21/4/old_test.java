  @Test
  public void registerCoder() throws IOException {
    Coder<?> coder =
        KvCoder.of(StringUtf8Coder.of(), IterableCoder.of(SetCoder.of(ByteArrayCoder.of())));
    String id = components.registerCoder(coder);
    assertThat(components.registerCoder(coder), equalTo(id));
    assertThat(id, not(isEmptyOrNullString()));
    Coder<?> equalCoder =
        KvCoder.of(StringUtf8Coder.of(), IterableCoder.of(SetCoder.of(ByteArrayCoder.of())));
    assertThat(components.registerCoder(equalCoder), equalTo(id));
    Coder<?> otherCoder = VarLongCoder.of();
    assertThat(components.registerCoder(otherCoder), not(equalTo(id)));

    components.toComponents().getCodersOrThrow(id);
    components.toComponents().getCodersOrThrow(components.registerCoder(otherCoder));
  }