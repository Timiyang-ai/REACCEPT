@VisibleForTesting
  static Map<Integer, Container> getContainers(
      PackingPlan currentPackingPlan, int paddingPercentage,
      Map<String, TreeSet<Integer>> componentIndexes, TreeSet<Integer> taskIds)
      throws ResourceExceededException {
    Map<Integer, Container> containers = new HashMap<>();

    Resource capacity = currentPackingPlan.getMaxContainerResources();
    for (PackingPlan.ContainerPlan currentContainerPlan : currentPackingPlan.getContainers()) {
      Container container =
          new Container(currentContainerPlan.getId(), capacity, paddingPercentage);
      for (PackingPlan.InstancePlan instancePlan : currentContainerPlan.getInstances()) {
        try {
          addToContainer(container, instancePlan, componentIndexes, taskIds);
        } catch (ResourceExceededException e) {
          throw new ResourceExceededException(String.format(
              "Insufficient container resources to add instancePlan %s to container %s",
              instancePlan, container), e);
        }
      }
      containers.put(currentContainerPlan.getId(), container);
    }
    return containers;
  }