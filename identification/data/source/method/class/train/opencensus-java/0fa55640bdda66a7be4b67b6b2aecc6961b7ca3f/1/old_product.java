public static Resource create(String clusterName, String namespace, String podName) {
    Map<String, String> labels = new LinkedHashMap<String, String>();
    labels.put(CLUSTER_NAME_KEY, checkNotNull(clusterName, "clusterName"));
    labels.put(NAMESPACE_NAME_KEY, checkNotNull(namespace, "namespace"));
    labels.put(POD_NAME_KEY, checkNotNull(podName, "podName"));
    return Resource.create(TYPE, labels);
  }