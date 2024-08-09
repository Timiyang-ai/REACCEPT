public static Matrix forwardAlgorithm(HmmModel model, int[] observations,
                                        boolean scaled) {

    DenseMatrix alpha = new DenseMatrix(observations.length, model
        .getNrOfHiddenStates());

    forwardAlgorithm(alpha, model, observations, scaled);

    return alpha;
  }