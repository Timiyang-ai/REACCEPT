  @Test
  public void buildDependencyTree() {
    MarketDataNode expected =
        rootNode(
            observableNode(new TestIdA("1")),
            valueNode(
                new TestIdB("2"),
                valueNode(
                    new TestIdB("4"),
                    observableNode(new TestIdA("5"))),
                timeSeriesNode(new TestIdA("3"))),
            timeSeriesNode(new TestIdA("6")));

    // The requirements for the data directly used by the calculations
    MarketDataRequirements requirements =
        MarketDataRequirements.builder()
            .addValues(new TestIdA("1"), new TestIdB("2"))
            .addTimeSeries(new TestIdA("6"))
            .build();

    // Requirements for each item in the tree - used to initialize the functions
    MarketDataRequirements id2Reqs =
        MarketDataRequirements.builder()
            .addTimeSeries(new TestIdA("3"))
            .addValues(new TestIdB("4"))
            .build();

    MarketDataRequirements id4Reqs =
        MarketDataRequirements.builder()
            .addValues(new TestIdA("5"))
            .build();

    ImmutableMap<TestIdB, MarketDataRequirements> reqsMap =
        ImmutableMap.of(
            new TestIdB("2"), id2Reqs,
            new TestIdB("4"), id4Reqs);

    TestMarketDataFunctionA builderA = new TestMarketDataFunctionA();
    TestMarketDataFunctionB builderB = new TestMarketDataFunctionB(reqsMap);

    ImmutableMap<Class<? extends MarketDataId<?>>, MarketDataFunction<?, ?>> functions =
        ImmutableMap.of(
            TestIdA.class, builderA,
            TestIdB.class, builderB);

    MarketDataNode root =
        MarketDataNode.buildDependencyTree(
            requirements,
            BuiltScenarioMarketData.empty(),
            MarketDataConfig.empty(),
            functions);

    assertThat(root).isEqualTo(expected);
  }