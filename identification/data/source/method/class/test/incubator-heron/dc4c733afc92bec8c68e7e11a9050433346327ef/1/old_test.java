@Test
  public void testGetContainers() throws ResourceExceededException {

    int paddingPercentage = 10;
    Map<Integer, List<InstanceId>> packing = new HashMap<>();
    packing.put(7, Arrays.asList(
        new InstanceId("spout", 1, 0),
        new InstanceId("bolt", 2, 0)));
    packing.put(3, Arrays.asList(
        new InstanceId("spout", 3, 0),
        new InstanceId("bolt", 4, 0)));

    PackingPlan packingPlan = generatePacking(packing);
    Map<Integer, Container> containers = PackingPlanBuilder.getContainers(
        packingPlan, paddingPercentage,
        new HashMap<String, TreeSet<Integer>>(), new TreeSet<Integer>());
    assertEquals(packing.size(), containers.size());
    for (Integer containerId : packing.keySet()) {
      Container foundContainer = containers.get(containerId);
      assertEquals(paddingPercentage, foundContainer.getPaddingPercentage());
      assertEquals(packingPlan.getMaxContainerResources(), foundContainer.getCapacity());
      assertEquals(2, foundContainer.getInstances().size());
    }
  }