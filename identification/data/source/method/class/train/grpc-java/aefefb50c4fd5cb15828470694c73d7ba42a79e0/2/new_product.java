private static ApplicationProtocolConfig selectApplicationProtocolConfig(SslProvider provider) {
    switch (provider) {
      case JDK: {
        if (JettyTlsUtil.isJettyAlpnConfigured()) {
          return ALPN;
        }
        if (JettyTlsUtil.isJettyNpnConfigured()) {
          return NPN;
        }
        throw new IllegalArgumentException("Jetty ALPN/NPN has not been properly configured.");
      }
      case OPENSSL: {
        if (!OpenSsl.isAvailable()) {
          throw new IllegalArgumentException("OpenSSL is not installed on the system.");
        }

        if (OpenSsl.isAlpnSupported()) {
          return NPN_AND_ALPN;
        } else {
          return NPN;
        }
      }
      default:
        throw new IllegalArgumentException("Unsupported provider: " + provider);
    }
  }