public static CipherSuite forJavaName(String javaName) {
    return javaName.startsWith("SSL_")
        ? valueOf("TLS_" + javaName.substring(4))
        : valueOf(javaName);
  }