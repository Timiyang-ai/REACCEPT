public void merge(final KllFloatsSketch other) {
    if (other == null || other.isEmpty()) { return; }
    if (m_ != other.m_) {
      throw new SketchesArgumentException("incompatible M: " + m_ + " and " + other.m_);
    }
    if (isEmpty()) {
      minValue_ = other.minValue_;
      maxValue_ = other.maxValue_;
    } else {
      if (other.minValue_ < minValue_) { minValue_ = other.minValue_; }
      if (other.maxValue_ > maxValue_) { maxValue_ = other.maxValue_; }
    }
    final long finalN = n_ + other.n_;
    if (other.numLevels_ >= 1) {
      for (int i = other.levels_[0]; i < other.levels_[1]; i++) {
        update(other.items_[i]);
      }
    }
    if (other.numLevels_ >= 2) {
      mergeHigherLevels(other, finalN);
    }
    n_ = finalN;
    assert_correct_total_weight();
    minK_ = Math.min(minK_, other.minK_);
  }