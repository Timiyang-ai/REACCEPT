SpanNode addChild(SpanNode child) {
    if (child == null) throw new NullPointerException("child == null");
    if (child == this) throw new IllegalArgumentException("circular dependency on " + this);
    if (children.equals(Collections.emptyList())) children = new ArrayList<>();
    if (!children.contains(child)) {
      children.add(child);
      child.parent = this;
    } else { // shouldn't ever happen as we already dedupe with Trace.merge
      throw new IllegalArgumentException("children already contains " + child);
    }
    return this;
  }