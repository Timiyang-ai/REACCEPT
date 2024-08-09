  @Test
  public void resolveHeaderMap() {
    BuildTarget target = BuildTargetFactory.newInstance("//hello/world:test");
    ImmutableMap<String, SourcePath> headerMap =
        ImmutableMap.of(
            "foo/bar.h", FakeSourcePath.of("header1.h"),
            "foo/hello.h", FakeSourcePath.of("header2.h"));

    // Verify that the resolveHeaderMap returns sane results.
    ImmutableMap<Path, SourcePath> expected =
        ImmutableMap.of(
            target
                    .getCellRelativeBasePath()
                    .getPath()
                    .toPathDefaultFileSystem()
                    .resolve("foo/bar.h"),
                FakeSourcePath.of("header1.h"),
            target
                    .getCellRelativeBasePath()
                    .getPath()
                    .toPathDefaultFileSystem()
                    .resolve("foo/hello.h"),
                FakeSourcePath.of("header2.h"));
    ImmutableMap<Path, SourcePath> actual =
        CxxPreprocessables.resolveHeaderMap(
            target.getCellRelativeBasePath().getPath().toPathDefaultFileSystem(), headerMap);
    assertEquals(expected, actual);
  }