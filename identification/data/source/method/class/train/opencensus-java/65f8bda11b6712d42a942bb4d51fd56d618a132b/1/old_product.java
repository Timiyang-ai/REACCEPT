public synchronized void advanceTime(Duration duration) {
    currentTime =
        validateNanos(
            Timestamp.create(
                    LongMath.checkedAdd(currentTime.getSeconds(), duration.getSeconds()),
                    currentTime.getNanos())
                .addNanos(duration.getNanos()));
  }