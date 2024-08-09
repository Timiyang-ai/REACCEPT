public static ReservedList create(
      String name, Boolean shouldPublish, Map<String, ReservedEntry> labelsToReservations) {
    return new ReservedList(name, shouldPublish, labelsToReservations);
  }