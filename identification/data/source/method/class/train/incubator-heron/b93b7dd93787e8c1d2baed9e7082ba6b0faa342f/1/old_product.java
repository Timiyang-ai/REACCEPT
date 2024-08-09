public static ArrayList<Container> getContainers(PackingPlan currentPackingPlan,
                                                   int paddingPercentage) {
    ArrayList<Container> containers = new ArrayList<>();

    //sort containers based on containerIds;
    PackingPlan.ContainerPlan[] currentContainers =
        PackingUtils.sortOnContainerId(currentPackingPlan.getContainers());

    Resource capacity = currentPackingPlan.getMaxContainerResources();
    for (int i = 0; i < currentContainers.length; i++) {
      int containerId = PackingUtils.allocateNewContainer(
          containers, capacity, paddingPercentage);
      for (PackingPlan.InstancePlan instancePlan
          : currentContainers[i].getInstances()) {
        containers.get(containerId - 1).add(instancePlan);
      }
    }
    return containers;
  }