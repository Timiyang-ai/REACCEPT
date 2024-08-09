private static ApplicationProtocolConfig selectApplicationProtocolConfig(SslProvider provider) {
    switch (provider) {
      case JDK: {
        if (JettyTlsUtil.isJettyAlpnConfigured()) {
          return ALPN;
        }
        if (JettyTlsUtil.isJettyNpnConfigured()) {
          return NPN;
        }
        // Use the ALPN cause since it is prefered.
        throw new IllegalArgumentException(
            "Jetty ALPN/NPN has not been properly configured.",
            JettyTlsUtil.getJettyAlpnUnavailabilityCause());
      }
      case OPENSSL: {
        if (!OpenSsl.isAvailable()) {
          throw new IllegalArgumentException(
              "OpenSSL is not installed on the system.", OpenSsl.unavailabilityCause());
        }
        return OpenSsl.isAlpnSupported() ? NPN_AND_ALPN : NPN;
      }
      default:
        throw new IllegalArgumentException("Unsupported provider: " + provider);
    }
  }