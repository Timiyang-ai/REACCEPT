public static Matrix forwardAlgorithm(HmmModel model, int[] observations,
                                        boolean scaled) {

    Matrix alpha = new DenseMatrix(observations.length, model.getNrOfHiddenStates());

    forwardAlgorithm(alpha, model, observations, scaled);

    return alpha;
  }