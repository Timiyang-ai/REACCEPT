  @Test
  public void merge() {
    // a    b
    // |    |
    // c1   c2
    //
    // a    b
    //  \  /
    //   c

    TargetNode<?> a =
        FakeTargetNodeBuilder.newBuilder(BuildTargetFactory.newInstance("//:a")).build();
    TargetNode<?> b =
        FakeTargetNodeBuilder.newBuilder(BuildTargetFactory.newInstance("//:b")).build();
    TargetNode<?> c1 =
        FakeTargetNodeBuilder.newBuilder(BuildTargetFactory.newInstance("//:c"))
            .setDeps(a.getBuildTarget())
            .build();
    TargetNode<?> c2 =
        FakeTargetNodeBuilder.newBuilder(
                BuildTargetFactory.newInstance("//:c")
                    .getUnconfiguredBuildTargetView()
                    .configure(ConfigurationBuildTargetFactoryForTests.newConfiguration("//:p")))
            .setDeps(b.getBuildTarget())
            .build();

    TargetGraph graph = TargetGraphFactory.newInstance(a, b, c1, c2);
    MergedTargetGraph merged = MergedTargetGraph.merge(graph);

    MergedTargetNode ma = merged.getIndex().get(a.getBuildTarget().getUnflavoredBuildTarget());
    MergedTargetNode mb = merged.getIndex().get(b.getBuildTarget().getUnflavoredBuildTarget());
    MergedTargetNode mc = merged.getIndex().get(c1.getBuildTarget().getUnflavoredBuildTarget());

    assertEquals(ImmutableSet.of(ma, mb), merged.getOutgoingNodesFor(mc));
    assertEquals(ImmutableSet.of(mc), merged.getIncomingNodesFor(ma));
    assertEquals(ImmutableSet.of(mc), merged.getIncomingNodesFor(mb));
  }