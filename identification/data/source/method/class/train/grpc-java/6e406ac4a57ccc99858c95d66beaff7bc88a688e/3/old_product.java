public static String getSelectedProtocol(SSLSocket socket) {
    if (!android) {
      return platform.getSelectedProtocol(socket);
    }

    if (GET_ALPN_SELECTED_PROTOCOL.isSupported(socket)) {
      try {
        byte[] alpnResult =
            (byte[]) GET_ALPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(socket);
        if (alpnResult != null) {
          return new String(alpnResult, Util.UTF_8);
        }
      } catch (Exception e) {
        // In some implementations, querying selected protocol before the handshake will fail with
        // exception.
      }
    }

    if (GET_NPN_SELECTED_PROTOCOL.isSupported(socket)) {
      try {
        byte[] npnResult = (byte[]) GET_NPN_SELECTED_PROTOCOL.invokeWithoutCheckedException(socket);
        if (npnResult != null) {
          return new String(npnResult, Util.UTF_8);
        }
      } catch (Exception e) {
        // In some implementations, querying selected protocol before the handshake will fail with
        // exception.
      }
    }
    return null;
  }