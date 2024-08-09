private static StatisticsProvider statsInit(List<Object> args) {
    int windowSize = 0;
    if(args.size() > 0 && args.get(0) instanceof Number) {
      windowSize = convert(args.get(0), Integer.class);
    }
    if(windowSize > 0) {
      return new WindowedStatisticsProvider(windowSize);
    }
    return new OnlineStatisticsProvider();
  }