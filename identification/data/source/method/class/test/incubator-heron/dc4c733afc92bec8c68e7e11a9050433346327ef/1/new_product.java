@VisibleForTesting
  static Map<Integer, Container> getContainers(
      PackingPlan currentPackingPlan, Resource maxContainerResource, Resource padding,
      Map<String, TreeSet<Integer>> componentIndexes, TreeSet<Integer> taskIds) {
    Map<Integer, Container> containers = new HashMap<>();

    Resource capacity = maxContainerResource;
    for (PackingPlan.ContainerPlan currentContainerPlan : currentPackingPlan.getContainers()) {
      Container container =
          new Container(currentContainerPlan.getId(), capacity, padding);
      for (PackingPlan.InstancePlan instancePlan : currentContainerPlan.getInstances()) {
        addToContainer(container, instancePlan, componentIndexes, taskIds);
      }
      containers.put(currentContainerPlan.getId(), container);
    }
    return containers;
  }