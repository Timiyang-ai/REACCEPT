public MutablePointSensitivities normalize() {
    sensitivities.sort(PointSensitivity::compareExcludingSensitivity);
    PointSensitivity previous = sensitivities.get(0);
    for (int i = 1; i < sensitivities.size(); i++) {
      PointSensitivity current = sensitivities.get(i);
      if (current.compareExcludingSensitivity(previous) == 0) {
        sensitivities.set(i - 1, previous.withSensitivity(previous.getSensitivity() + current.getSensitivity()));
        sensitivities.remove(i);
        i--;
      }
      previous = current;
    }
    return this;
  }