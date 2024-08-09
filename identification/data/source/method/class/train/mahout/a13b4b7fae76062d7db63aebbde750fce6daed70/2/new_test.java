@Test
  public void testProcessOutput() throws Exception {
    Random rng = RandomUtils.getRandom();
    //long seed = rng.nextLong();

    // create a dataset large enough to be split up
    String descriptor = Utils.randomDescriptor(rng, NUM_ATTRIBUTES);
    double[][] source = Utils.randomDoubles(rng, descriptor, NUM_INSTANCES);

    // each instance label is its index in the dataset
    int labelId = Utils.findLabel(descriptor);
    for (int index = 0; index < NUM_INSTANCES; index++) {
      source[index][labelId] = index;
    }

    // store the data into a file
    String[] sData = Utils.double2String(source);
    Path dataPath = Utils.writeDataToTestFile(sData);
    Dataset dataset = DataLoader.generateDataset(descriptor, sData);
    Data data = DataLoader.loadData(dataset, sData);

    Configuration conf = new Configuration();
    Step0JobTest.setMaxSplitSize(conf, dataPath, NUM_MAPS);

    // prepare a custom TreeBuilder that will classify each
    // instance with its own label (in this case its index in the dataset)
    TreeBuilder treeBuilder = new MockTreeBuilder();
    
    // disable the second step because we can test without it
    // and we won't be able to serialize the MockNode
    PartialBuilder.setStep2(conf, false);
    long seed = 1L;
    Builder builder = new PartialSequentialBuilder(treeBuilder, dataPath, dataset, seed, conf);

    // remove the output path (its only used for testing)
    Path outputPath = builder.getOutputPath(conf);
    HadoopUtil.delete(conf, outputPath);

    builder.build(NUM_TREES, new MockCallback(data));
  }