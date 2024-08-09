public static synchronized CipherSuite forJavaName(String javaName) {
    CipherSuite result = INSTANCES.get(javaName);
    if (result == null) {
      result = new CipherSuite(javaName);
      INSTANCES.put(javaName, result);
    }
    return result;
  }