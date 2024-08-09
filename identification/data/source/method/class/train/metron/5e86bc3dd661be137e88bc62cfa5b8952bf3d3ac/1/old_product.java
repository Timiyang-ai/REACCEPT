private static StellarStatistics statsInit(List<Object> args) {
    int windowSize = 0;
    if(args.size() > 0 && args.get(0) instanceof Number) {
      windowSize = convert(args.get(0), Integer.class);
    }

    return new StellarStatistics(windowSize);
  }