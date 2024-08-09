  @Test
  public void withLeavesRemoved() {
    MarketDataNode root =
        rootNode(
            observableNode(new TestIdA("1")),
            valueNode(
                new TestIdB("2"),
                valueNode(new TestIdB("3")),
                observableNode(new TestIdA("4")),
                valueNode(
                    new TestIdB("5"),
                    timeSeriesNode(new TestIdA("6")))),
            valueNode(new TestIdB("7")));

    Pair<MarketDataNode, MarketDataRequirements> pair1 = root.withLeavesRemoved();

    MarketDataRequirements expectedReqs1 =
        MarketDataRequirements.builder()
            .addValues(new TestIdA("1"))
            .addValues(new TestIdB("3"))
            .addValues(new TestIdA("4"))
            .addTimeSeries(new TestIdA("6"))
            .addValues(new TestIdB("7"))
            .build();

    MarketDataNode expectedTree1 =
        rootNode(
            valueNode(
                new TestIdB("2"),
                valueNode(
                    new TestIdB("5"))));

    MarketDataNode tree1 = pair1.getFirst();
    MarketDataRequirements reqs1 = pair1.getSecond();

    assertThat(tree1).isEqualTo(expectedTree1);
    assertThat(expectedReqs1).isEqualTo(reqs1);

    Pair<MarketDataNode, MarketDataRequirements> pair2 = tree1.withLeavesRemoved();

    MarketDataRequirements expectedReqs2 =
        MarketDataRequirements.builder()
            .addValues(new TestIdB("5"))
            .build();

    MarketDataNode expectedTree2 =
        rootNode(
            valueNode(
                new TestIdB("2")));

    MarketDataNode tree2 = pair2.getFirst();
    MarketDataRequirements reqs2 = pair2.getSecond();

    assertThat(tree2).isEqualTo(expectedTree2);
    assertThat(expectedReqs2).isEqualTo(reqs2);

    Pair<MarketDataNode, MarketDataRequirements> pair3 = tree2.withLeavesRemoved();

    MarketDataRequirements expectedReqs3 =
        MarketDataRequirements.builder()
            .addValues(new TestIdB("2"))
            .build();

    MarketDataNode tree3 = pair3.getFirst();
    MarketDataRequirements reqs3 = pair3.getSecond();

    assertThat(tree3.isLeaf()).isTrue();
    assertThat(expectedReqs3).isEqualTo(reqs3);
  }