static Map<Integer, Container> getContainers(PackingPlan currentPackingPlan,
                                               int paddingPercentage) {
    Map<Integer, Container> containers = new HashMap<>();

    //sort containers based on containerIds;
    PackingPlan.ContainerPlan[] currentContainerPlans =
        PackingUtils.sortOnContainerId(currentPackingPlan.getContainers());

    Resource capacity = currentPackingPlan.getMaxContainerResources();
    for (PackingPlan.ContainerPlan currentContainerPlan : currentContainerPlans) {
      Container container = new Container(capacity, paddingPercentage);
      for (PackingPlan.InstancePlan instancePlan : currentContainerPlan.getInstances()) {
        container.add(instancePlan);
      }
      containers.put(currentContainerPlan.getId(), container);
    }
    return containers;
  }