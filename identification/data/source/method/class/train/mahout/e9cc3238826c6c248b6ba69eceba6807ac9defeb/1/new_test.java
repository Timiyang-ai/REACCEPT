@Test
  public void testGenerateDataset() throws Exception {
    int nbAttributes = 10;
    int datasize = 100;

    // prepare the descriptors
    String descriptor = Utils.randomDescriptor(rng, nbAttributes);
    Attribute[] attrs = DescriptorUtils.parseDescriptor(descriptor);

    // prepare the data
    double[][] data = Utils.randomDoubles(rng, descriptor, false, datasize);
    Collection<Integer> missings = Lists.newArrayList();
    String[] sData = prepareData(data, attrs, missings);
    Dataset expected = DataLoader.generateDataset(descriptor, false, sData);

    Dataset dataset = DataLoader.generateDataset(descriptor, false, sData);
    
    assertEquals(expected, dataset);

    // regression
    data = Utils.randomDoubles(rng, descriptor, true, datasize);
    missings = Lists.newArrayList();
    sData = prepareData(data, attrs, missings);
    expected = DataLoader.generateDataset(descriptor, true, sData);

    dataset = DataLoader.generateDataset(descriptor, true, sData);
    
    assertEquals(expected, dataset);
}