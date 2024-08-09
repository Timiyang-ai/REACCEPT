  @Test
  public void serialize() throws IOException {
    ImmutableMap<RuleKey, ImmutableMap<String, HashCode>> entries =
        ImmutableMap.of(new RuleKey("aa"), ImmutableMap.of("foo/bar.h", HashCode.fromInt(20)));
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ManifestUtil.fromMap(new RuleKey("cc"), entries).serialize(byteArrayOutputStream);
    Manifest deserialized =
        new Manifest(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
    assertThat(ManifestUtil.toMap(deserialized), Matchers.equalTo(entries));
  }