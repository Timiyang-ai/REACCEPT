@Test
  public void testGenerateDataset() throws Exception {
    int nbAttributes = 10;
    int datasize = 100;

    // prepare the descriptors
    String descriptor = Utils.randomDescriptor(rng, nbAttributes);
    Attribute[] attrs = DescriptorUtils.parseDescriptor(descriptor);

    // prepare the data
    double[][] data = Utils.randomDoubles(rng, descriptor, datasize);
    Collection<Integer> missings = Lists.newArrayList();
    String[] sData = prepareData(data, attrs, missings);
    Dataset expected = DataLoader.generateDataset(descriptor, sData);

    Dataset dataset = DataLoader.generateDataset(descriptor, sData);
    
    assertEquals(expected, dataset);
  }