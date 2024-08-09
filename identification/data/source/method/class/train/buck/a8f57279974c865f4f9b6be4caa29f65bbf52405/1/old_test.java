  @Test
  public void resolveDuplicateRelativePaths() {
    BuildRuleResolver ruleResolver = new TestActionGraphBuilder();
    tmp.getRoot().resolve("one").toFile().mkdir();
    tmp.getRoot().resolve("two").toFile().mkdir();
    ProjectFilesystem fsOne =
        TestProjectFilesystems.createProjectFilesystem(tmp.getRoot().resolve("one"));
    ProjectFilesystem fsTwo =
        TestProjectFilesystems.createProjectFilesystem(tmp.getRoot().resolve("two"));

    ImmutableBiMap<SourcePath, Path> expected =
        ImmutableBiMap.of(
            FakeSourcePath.of(fsOne, "a/one.a"), Paths.get("a/one.a"),
            FakeSourcePath.of(fsOne, "a/two"), Paths.get("a/two"),
            FakeSourcePath.of(fsTwo, "a/one.a"), Paths.get("a/one-1.a"),
            FakeSourcePath.of(fsTwo, "a/two"), Paths.get("a/two-1"));

    ImmutableBiMap<SourcePath, Path> resolvedDuplicates =
        SymlinkTree.resolveDuplicateRelativePaths(
            ImmutableSortedSet.copyOf(expected.keySet()), ruleResolver.getSourcePathResolver());

    assertThat(resolvedDuplicates, Matchers.equalTo(expected));
  }