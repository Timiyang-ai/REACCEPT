@VisibleForTesting
    static ScanMarker getComparedMarker(ScanMarker lStartMarker, ScanMarker rStartMarker,
        boolean getGreater) {
      // if one of them has null bytes, just return other
      if(lStartMarker.bytes == null) {
        return rStartMarker;
      } else if (rStartMarker.bytes == null) {
        return lStartMarker;
      }

      int compareRes = compare(lStartMarker.bytes, rStartMarker.bytes);
      if (compareRes == 0) {
        // bytes are equal, now compare the isInclusive flags
        if (lStartMarker.isInclusive == rStartMarker.isInclusive) {
          // actually equal, so return any one
          return lStartMarker;
        }
        boolean isInclusive = true;
        // one that does not include the current bytes is greater
        if (getGreater) {
          isInclusive = false;
        }
        // else
        return new ScanMarker(lStartMarker.bytes, isInclusive);
      }
      if (getGreater) {
        return compareRes == 1 ? lStartMarker : rStartMarker;
      }
      // else
      return compareRes == -1 ? lStartMarker : rStartMarker;
    }