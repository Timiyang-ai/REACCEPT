final boolean parseTraceId(String traceIdString, Object key) {
      if (isNull(key, traceIdString)) return false;
      int length = traceIdString.length();
      if (invalidIdLength(key, length, 32)) return false;

      // left-most characters, if any, are the high bits
      int traceIdIndex = Math.max(0, length - 16);
      if (traceIdIndex > 0) {
        traceIdHigh = lenientLowerHexToUnsignedLong(traceIdString, 0, traceIdIndex);
        if (traceIdHigh == 0) {
          maybeLogNotLowerHex(traceIdString);
          return false;
        }
      }

      // right-most up to 16 characters are the low bits
      traceId = lenientLowerHexToUnsignedLong(traceIdString, traceIdIndex, length);
      if (traceId == 0) {
        maybeLogNotLowerHex(traceIdString);
        return false;
      }
      return true;
    }