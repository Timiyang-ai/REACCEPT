final <C, K> boolean parseSpanId(Propagation.Getter<C, K> getter, C carrier, K key) {
      String spanIdString = getter.get(carrier, key);
      if (isNull(key, spanIdString)) return false;
      int length = spanIdString.length();
      if (invalidIdLength(key, length, 16)) return false;

      spanId = lenientLowerHexToUnsignedLong(spanIdString, 0, length);
      if (spanId == 0) {
        maybeLogNotLowerHex(key, spanIdString);
        return false;
      }
      return true;
    }