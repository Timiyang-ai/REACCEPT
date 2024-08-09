@Test
  public void testInit() throws Exception
  {
    NewTopologyBuilder b = new NewTopologyBuilder();

    Topology.NodeDecl generatorNode = b.addNode("generatorNode", NumberGeneratorInputAdapter.class);
    Topology.NodeDecl node1 = b.addNode("node1", GenericTestNode.class);

    NewTopologyBuilder.StreamBuilder generatorOutput = b.addStream("generatorOutput");
    generatorOutput.setSource(generatorNode.getOutput(NumberGeneratorInputAdapter.OUTPUT_PORT))
            .addSink(node1.getInput(GenericTestNode.INPUT1))
            .setSerDeClass(DNodeManagerTest.TestStaticPartitioningSerDe.class);

    //StreamConf output1 = b.getOrAddStream("output1");
    //output1.addProperty(TopologyBuilder.STREAM_CLASSNAME,
    //                    ConsoleOutputStream.class.getName());
    Topology tplg = b.getTopology();

    DNodeManager dnm = new DNodeManager(tplg);
    int expectedContainerCount = DNodeManagerTest.TestStaticPartitioningSerDe.partitions.length;
    Assert.assertEquals("number required containers",
                        expectedContainerCount,
                        dnm.getNumRequiredContainers());

    List<StramLocalCluster.LocalStramChild> containers = new ArrayList<StramLocalCluster.LocalStramChild>();

    for (int i = 0; i < expectedContainerCount; i++) {
      String containerId = "container" + (i + 1);
      StreamingNodeUmbilicalProtocol.StreamingContainerContext cc = dnm.assignContainerForTest(containerId, InetSocketAddress.createUnresolved("localhost", bufferServerPort));
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