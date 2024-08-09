public void addFact(Expression e, Fact fact) {
    // Recursively fold e, since that is how the optimizer will compare it.
    // This has the side effect of turning complex expressions like (1/0),
    // (0/0), and (-0) into NumberLiterals.
    addFactInt(rfold(e, false), fact);
  }