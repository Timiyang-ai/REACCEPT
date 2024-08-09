@Deprecated
  public static Resource create(String clusterName, String namespace, String podName) {
    return create(clusterName, namespace, podName, "");
  }