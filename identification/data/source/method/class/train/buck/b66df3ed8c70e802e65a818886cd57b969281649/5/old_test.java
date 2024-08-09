  @Test
  public void addEntry() throws IOException {
    Manifest manifest = new Manifest(new RuleKey("cc"));
    RuleKey key = new RuleKey("aa");
    SourcePath input = FakeSourcePath.of("input.h");
    HashCode hashCode = HashCode.fromInt(20);
    FileHashLoader fileHashLoader =
        new FakeFileHashCache(ImmutableMap.of(RESOLVER.getAbsolutePath(input), hashCode));
    manifest.addEntry(
        fileHashLoader, key, RESOLVER, ImmutableSet.of(input), ImmutableSet.of(input));
    assertThat(
        ManifestUtil.toMap(manifest),
        Matchers.equalTo(
            ImmutableMap.of(
                key, ImmutableMap.of(RESOLVER.getRelativePath(input).toString(), hashCode))));
  }