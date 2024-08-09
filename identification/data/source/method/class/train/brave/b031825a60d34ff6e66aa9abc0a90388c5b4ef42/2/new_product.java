@Override public boolean equals(Object o) {
    if (o == this) return true;
    return isEqualToRealOrLazySpan(context, o);
  }