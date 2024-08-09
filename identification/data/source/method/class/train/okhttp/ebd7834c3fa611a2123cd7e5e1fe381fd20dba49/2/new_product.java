public static synchronized CipherSuite forJavaName(String javaName) {
    CipherSuite result = INSTANCES.get(javaName);
    if (result == null) {
      result = INSTANCES.get(secondaryName(javaName));

      if (result == null) {
        result = new CipherSuite(javaName);
      }

      // Add the new cipher suite, or a confirmed alias.
      INSTANCES.put(javaName, result);
    }
    return result;
  }