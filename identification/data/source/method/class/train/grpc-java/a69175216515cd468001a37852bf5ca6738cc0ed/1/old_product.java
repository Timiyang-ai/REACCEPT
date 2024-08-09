private static OkHttpProtocolNegotiator createNegotiator() {
    boolean android = true;
    try {
      // Attempt to find Android 2.3+ APIs.
      Class.forName("com.android.org.conscrypt.OpenSSLSocketImpl");
    } catch (ClassNotFoundException ignored) {
      try {
        // Older platform before being unbundled.
        Class.forName("org.apache.harmony.xnet.provider.jsse.OpenSSLSocketImpl");
      } catch (ClassNotFoundException ignored2) {
        android = false;
      }
    }
    return android ? new AndroidNegotiator() : new OkHttpProtocolNegotiator();
  }