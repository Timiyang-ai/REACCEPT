public void markPoint(String eventName, String stage, String dataAttribution) {
    if (stage.equals(START)) {
      mStartedEvents.add(getFullMarkerName(eventName, dataAttribution, ""));
    } else if (stage.equals(END)
        && !mStartedEvents.remove(getFullMarkerName(eventName, dataAttribution, ""))) {
      // no matching start point, skip (can happen for changeset end)
      return;
    }
    markPoint(getFullMarkerName(eventName, dataAttribution, stage));
  }