  public static StreamingContainerAgent assignContainer(StreamingContainerManager scm, String containerId)
  {
    return scm.assignContainer(new ContainerResource(0, containerId, "localhost", 1024, 0, null), InetSocketAddress.createUnresolved(containerId + "Host", 0));
  }