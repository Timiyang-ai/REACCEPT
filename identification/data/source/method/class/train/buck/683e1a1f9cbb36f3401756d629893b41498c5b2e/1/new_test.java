  @Test
  public void group() {
    TargetNode<?> qux =
        FakeTargetNodeBuilder.newBuilder(BuildTargetFactory.newInstance("//foo:qux")).build();
    TargetNode<FakeTargetNodeArg> bar1 =
        FakeTargetNodeBuilder.newBuilder(BuildTargetFactory.newInstance("//foo:bar")).build();
    TargetNode<FakeTargetNodeArg> bar2 =
        FakeTargetNodeBuilder.newBuilder(BuildTargetFactory.newInstance("//foo:bar#baz")).build();
    TargetNode<FakeTargetNodeArg> bar3 =
        FakeTargetNodeBuilder.newBuilder(
                BuildTargetFactory.newInstance("//foo:bar")
                    .getUnconfiguredBuildTargetView()
                    .configure(ConfigurationBuildTargetFactoryForTests.newConfiguration("//:p")))
            .build();
    ImmutableMap<UnflavoredBuildTarget, MergedTargetNode> groups =
        MergedTargetNode.group(ImmutableList.of(bar1, bar2, bar3, qux));

    assertEquals(
        ImmutableList.of(qux),
        groups.get(qux.getBuildTarget().getUnflavoredBuildTarget()).getNodes());
    assertEquals(
        Stream.of(bar1, bar2, bar3).sorted().collect(ImmutableList.toImmutableList()),
        groups.get(bar1.getBuildTarget().getUnflavoredBuildTarget()).getNodes());
  }