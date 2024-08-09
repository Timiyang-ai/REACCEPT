static void backwardAlgorithm(Matrix beta, HmmModel model,
                                int[] observations, boolean scaled) {
    // fetch references to the model parameters
    Matrix b = model.getEmissionMatrix();
    Matrix a = model.getTransitionMatrix();

    if (scaled) { // compute log-scaled factors
      // initialization
      for (int i = 0; i < model.getNrOfHiddenStates(); i++)
        beta.setQuick(observations.length - 1, i, 0);

      // induction
      for (int t = observations.length - 2; t >= 0; t--) {
        for (int i = 0; i < model.getNrOfHiddenStates(); i++) {
          double sum = Double.NEGATIVE_INFINITY; // log(0)
          for (int j = 0; j < model.getNrOfHiddenStates(); j++) {
            double tmp = beta.getQuick(t + 1, j) + Math.log(a.getQuick(i, j))
                + Math.log(b.getQuick(j, observations[t + 1]));
            if (tmp > Double.NEGATIVE_INFINITY) // handle log(0)
              sum = tmp + Math.log(1 + Math.exp(sum - tmp));
          }
          beta.setQuick(t, i, sum);
        }
      }
    } else {
      // initialization
      for (int i = 0; i < model.getNrOfHiddenStates(); i++)
        beta.setQuick(observations.length - 1, i, 1);
      // induction
      for (int t = observations.length - 2; t >= 0; t--) {
        for (int i = 0; i < model.getNrOfHiddenStates(); i++) {
          double sum = 0;
          for (int j = 0; j < model.getNrOfHiddenStates(); j++)
            sum += beta.getQuick(t + 1, j) * a.getQuick(i, j)
                * b.getQuick(j, observations[t + 1]);
          beta.setQuick(t, i, sum);
        }
      }
    }
  }