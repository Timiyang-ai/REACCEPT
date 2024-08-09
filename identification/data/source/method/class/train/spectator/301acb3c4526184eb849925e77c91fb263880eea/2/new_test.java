  private List<List<Measurement>> getBatches() {
    long step = 10000;
    if (clock.wallTime() == 0L) {
      clock.setWallTime(step);
    }
    long t = clock.wallTime() / step * step;
    registry.pollMeters(t);
    return registry.getBatches(t);
  }