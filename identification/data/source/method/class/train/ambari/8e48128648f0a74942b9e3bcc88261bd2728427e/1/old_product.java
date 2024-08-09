public boolean isHashCached(String hostName) {
    if (null == hostName) {
      return false;
    }

    return m_hashes.containsKey(hostName);
  }