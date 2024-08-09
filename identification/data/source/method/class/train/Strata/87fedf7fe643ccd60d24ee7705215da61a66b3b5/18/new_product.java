public PointSensitivities normalized() {
    if (sensitivities.isEmpty()) {
      return this;
    }
    List<PointSensitivity> mutable = new ArrayList<>();
    for (PointSensitivity sensi : sensitivities) {
      insert(mutable, sensi);
    }
    return new PointSensitivities(mutable);
  }