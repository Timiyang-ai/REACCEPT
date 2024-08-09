public Builder traceId(String traceId) {
      checkNotNull(traceId, "traceId");
      if (traceId.length() == 32) {
        traceIdHigh(lowerHexToUnsignedLong(traceId, 0));
      }
      return traceId(lowerHexToUnsignedLong(traceId));
    }