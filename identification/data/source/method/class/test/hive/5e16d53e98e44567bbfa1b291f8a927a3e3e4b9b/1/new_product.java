@VisibleForTesting
    static ScanMarker getComparedMarker(ScanMarker lStartMarker, ScanMarker rStartMarker,
        boolean getGreater) {
      // if one of them has null bytes, just return other
      if(lStartMarker == null) {
        return rStartMarker;
      } else if (rStartMarker == null) {
        return lStartMarker;
      }
      TypeInfo expectedType =
          TypeInfoUtils.getTypeInfoFromTypeString(lStartMarker.type);
      ObjectInspector outputOI =
          TypeInfoUtils.getStandardWritableObjectInspectorFromTypeInfo(expectedType);
      Converter lConverter = ObjectInspectorConverters.getConverter(
          PrimitiveObjectInspectorFactory.javaStringObjectInspector, outputOI);
      Converter rConverter = ObjectInspectorConverters.getConverter(
          PrimitiveObjectInspectorFactory.javaStringObjectInspector, outputOI);
      Comparable lValue = (Comparable)lConverter.convert(lStartMarker.value);
      Comparable rValue = (Comparable)rConverter.convert(rStartMarker.value);

      int compareRes = lValue.compareTo(rValue);
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
        return new ScanMarker(lStartMarker.value, isInclusive, lStartMarker.type);
      }
      if (getGreater) {
        return compareRes == 1 ? lStartMarker : rStartMarker;
      }
      // else
      return compareRes == -1 ? lStartMarker : rStartMarker;
    }