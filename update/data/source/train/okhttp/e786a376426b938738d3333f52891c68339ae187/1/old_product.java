public static CipherSuite forJavaName(String javaName) {
    CipherSuite result = INSTANCES.get(javaName);
    if (result == null) {
      CipherSuite sample = new CipherSuite(javaName);
      CipherSuite canonical = INSTANCES.putIfAbsent(javaName, sample);
      result = (canonical == null) ? sample : canonical;
    }
    return result;
  }