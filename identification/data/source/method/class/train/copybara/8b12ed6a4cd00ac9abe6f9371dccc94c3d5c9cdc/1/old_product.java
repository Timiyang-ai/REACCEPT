String convert(String originRef) {
    if (!origin.contains("*")) {
      Preconditions.checkArgument(originRef.equals(origin),
          "originRef=%s origin=%s", originRef, origin);
      return destination;
    } else {
      List<String> origSplit = Splitter.on('*').splitToList(origin);
      Preconditions.checkState(origSplit.size() == 2);
      String fromPrefix = origSplit.get(0);
      String fromSuffix = origSplit.get(1);
      Preconditions.checkArgument(
          originRef.startsWith(fromPrefix) && originRef.endsWith(fromSuffix),
          "originRef=%s origin=%s", originRef, origin);
      String middle = originRef.substring(fromPrefix.length(),
          originRef.length() - fromSuffix.length());

      List<String> destSplit = Splitter.on('*').splitToList(destination);
      Preconditions.checkState(destSplit.size() == 2);
      String toPrefix = destSplit.get(0);
      String toSuffix = destSplit.get(1);
      return toPrefix + middle + toSuffix;
    }
  }