@Test
  public void testGetContainers() {

    int paddingPercentage = 10;
    Map<Integer, List<InstanceId>> packing = new HashMap<>();
    packing.put(1, Arrays.asList(
        new InstanceId("spout", 1, 0),
        new InstanceId("bolt", 2, 0)));
    packing.put(2, Arrays.asList(
        new InstanceId("spout", 3, 0),
        new InstanceId("bolt", 4, 0)));

    PackingPlan packingPlan = generatePacking(packing);
    ArrayList<Container> containers = PackingUtils.getContainers(packingPlan, paddingPercentage);
    Assert.assertEquals(2, containers.size());
    for (int i = 0; i < 2; i++) {
      Assert.assertEquals(paddingPercentage, containers.get(i).getPaddingPercentage());
      Assert.assertEquals(packingPlan.getMaxContainerResources(), containers.get(i).getCapacity());
      Assert.assertEquals(2, containers.get(i).getInstances().size());
    }
  }