@Override
  public float lengthNorm(FieldInvertState state) {
    final int numTokens;

    if (discountOverlaps)
      numTokens = state.getLength() - state.getNumOverlap();
    else
      numTokens = state.getLength();

    return state.getBoost() * computeLengthNorm(numTokens);
  }