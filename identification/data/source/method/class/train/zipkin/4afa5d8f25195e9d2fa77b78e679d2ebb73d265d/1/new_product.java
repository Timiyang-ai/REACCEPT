SpanNode addChild(SpanNode child) {
    if (child == null) throw new NullPointerException("child == null");
    if (child == this) throw new IllegalArgumentException("circular dependency on " + this);
    if (children.equals(Collections.emptyList())) children = new ArrayList<>();
    children.add(child);
    child.parent = this;
    return this;
  }