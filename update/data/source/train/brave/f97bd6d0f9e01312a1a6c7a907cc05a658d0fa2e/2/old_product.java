final <C, K> boolean parseParentId(Propagation.Getter<C, K> getter, C carrier, K key) {
      String parentIdString = getter.get(carrier, key);
      if (parentIdString == null) return true; // absent parent is ok
      int length = parentIdString.length();
      if (invalidIdLength(key, length, 16)) return false;

      parentId = lenientLowerHexToUnsignedLong(parentIdString, 0, length);
      if (parentId == 0) {
        maybeLogNotLowerHex(key, parentIdString);
        return false;
      }
      return true;
    }