@Test
  public void testInit() throws Exception
  {
    DAG dag = new DAG();

    DAG.Operator generator = dag.addOperator("generator", TestGeneratorInputModule.class);
    DAG.Operator operator1 = dag.addOperator("operator1", GenericTestModule.class);

    DAG.StreamDecl generatorOutput = dag.addStream("generatorOutput");
    generatorOutput.setSource(generator.getOutput(TestGeneratorInputModule.OUTPUT_PORT))
            .addSink(operator1.getInput(GenericTestModule.INPUT1))
            .setSerDeClass(ModuleManagerTest.TestStaticPartitioningSerDe.class);

    //StreamConf output1 = b.getOrAddStream("output1");
    //output1.addProperty(TopologyBuilder.STREAM_CLASSNAME,
    //                    ConsoleOutputStream.class.getName());

    ModuleManager dnm = new ModuleManager(dag);
    int expectedContainerCount = ModuleManagerTest.TestStaticPartitioningSerDe.partitions.length;
    Assert.assertEquals("number required containers",
                        expectedContainerCount,
                        dnm.getNumRequiredContainers());

    List<StramLocalCluster.LocalStramChild> containers = new ArrayList<StramLocalCluster.LocalStramChild>();

    for (int i = 0; i < expectedContainerCount; i++) {
      String containerId = "container" + (i + 1);
      StreamingContainerUmbilicalProtocol.StreamingContainerContext cc = dnm.assignContainerForTest(containerId, InetSocketAddress.createUnresolved("localhost", bufferServerPort));
      StramLocalCluster.LocalStramChild container = new StramLocalCluster.LocalStramChild(containerId, null, null);
      container.setup(cc);
      containers.add(container);
    }

    // TODO: validate data flow

    for (StramLocalCluster.LocalStramChild cc: containers) {
      logger.info("shutting down " + cc.getContainerId());
      cc.deactivate();
      cc.teardown();
    }
  }