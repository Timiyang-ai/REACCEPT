@Override
  public void computeNorm(FieldInvertState state, Norm norm) {
    final int numTokens;

    if (discountOverlaps)
      numTokens = state.getLength() - state.getNumOverlap();
    else
      numTokens = state.getLength();

    norm.setByte(encodeNormValue(state.getBoost() * computeLengthNorm(numTokens)));
  }