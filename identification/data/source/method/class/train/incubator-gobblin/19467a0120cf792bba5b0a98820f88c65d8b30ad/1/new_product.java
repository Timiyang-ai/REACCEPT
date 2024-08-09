public static CountEventBuilder fromEvent(GobblinTrackingEvent event) {
    if(!isCountEvent(event)) {
      return null;
    }

    Map<String, String> metadata = event.getMetadata();
    int count = Integer.parseInt(metadata.getOrDefault(COUNT_KEY, "0"));
    CountEventBuilder countEventBuilder = new CountEventBuilder(event.getName(), count);
    metadata.forEach((key, value) -> {
      switch (key) {
        case COUNT_KEY:
          break;
        default:
          countEventBuilder.addMetadata(key, value);
          break;
      }
    });

    return countEventBuilder;
  }