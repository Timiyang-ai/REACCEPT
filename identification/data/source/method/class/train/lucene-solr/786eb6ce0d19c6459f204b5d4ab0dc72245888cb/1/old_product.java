@Override
  public byte computeNorm(FieldInvertState state) {
    final int numTokens;

    if (discountOverlaps)
      numTokens = state.getLength() - state.getNumOverlap();
    else
      numTokens = state.getLength();

    return encodeNormValue(state.getBoost() * computeLengthNorm(numTokens));
  }