public ForwardRelativePath resolve(ForwardRelativePath other) {
    if (this.isEmpty()) {
      return other;
    } else if (other.isEmpty()) {
      return this;
    } else {
      // skip validation
      String[] segments = new String[this.segments.length + other.segments.length];
      System.arraycopy(this.segments, 0, segments, 0, this.segments.length);
      System.arraycopy(other.segments, 0, segments, this.segments.length, other.segments.length);
      return new ForwardRelativePath(segments);
    }
  }