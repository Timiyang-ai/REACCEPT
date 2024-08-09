public void setTimestamp(final String timestamp) {
    this.timestamp = timestamp;

    if (this.timestamp == null) {
      this.timestamp = DEFAULT_TIMESTAMP;
    }

    try {
      long l = Long.parseLong(this.timestamp);
      if (l < 0) {
        throw new IllegalArgumentException("Timestamp(" + timestamp + ") must be a Long type.");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Timestamp(" + timestamp + ") must be a Long type.");
    }
  }