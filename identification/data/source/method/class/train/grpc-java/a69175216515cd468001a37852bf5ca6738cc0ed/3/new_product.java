@VisibleForTesting
  static OkHttpProtocolNegotiator createNegotiator(ClassLoader loader) {
    boolean android = true;
    try {
      // Attempt to find Android 2.3+ APIs.
      loader.loadClass("com.android.org.conscrypt.OpenSSLSocketImpl");
    } catch (ClassNotFoundException e1) {
      logger.log(Level.FINE, "Unable to find Conscrypt. Skipping", e1);
      try {
        // Older platform before being unbundled.
        loader.loadClass("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
      } catch (ClassNotFoundException e2) {
        logger.log(Level.FINE, "Unable to find any OpenSSLSocketImpl. Skipping", e2);
        android = false;
      }
    }
    return android
        ? new AndroidNegotiator(DEFAULT_PLATFORM, AndroidNegotiator.DEFAULT_TLS_EXTENSION_TYPE)
        : new OkHttpProtocolNegotiator(DEFAULT_PLATFORM);
  }