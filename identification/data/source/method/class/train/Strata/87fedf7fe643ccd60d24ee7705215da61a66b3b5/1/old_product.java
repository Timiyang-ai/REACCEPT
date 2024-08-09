public PointSensitivities normalized() {
    if (sensitivities.isEmpty()) {
      return this;
    }
    List<PointSensitivity> mutable = new ArrayList<>(sensitivities);
    mutable.sort(PointSensitivity::compareExcludingSensitivity);
    PointSensitivity last = mutable.get(0);
    for (int i = 1; i < mutable.size(); i++) {
      PointSensitivity current = mutable.get(i);
      if (current.compareExcludingSensitivity(last) == 0) {
        mutable.set(i - 1, last.withSensitivity(last.getSensitivity() + current.getSensitivity()));
        mutable.remove(i);
        i--;
      }
      last = current;
    }
    return new PointSensitivities(mutable);
  }