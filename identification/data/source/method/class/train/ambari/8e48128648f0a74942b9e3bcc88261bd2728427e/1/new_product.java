public boolean isHashCached(String clusterName, String hostName) {
    if (null == clusterName || null == hostName) {
      return false;
    }

    Map<String, String> clusterMapping = m_hashes.get(hostName);
    if (null == clusterMapping) {
      return false;
    }

    return clusterMapping.containsKey(clusterName);
  }