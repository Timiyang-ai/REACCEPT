public default ReferenceData combinedWith(ReferenceData other) {
    return new CombinedReferenceData(this, other);
  }