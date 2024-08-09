public float computeNorm(String fieldName, FieldInvertState state) {
    final int numTokens;
    boolean overlaps = discountOverlaps;
    if (ln_overlaps.containsKey(fieldName)) {
      overlaps = ((Boolean)ln_overlaps.get(fieldName)).booleanValue();
    }
    if (overlaps)
      numTokens = state.getLength() - state.getNumOverlap();
    else
      numTokens = state.getLength();

    return state.getBoost() * lengthNorm(fieldName, numTokens);
  }