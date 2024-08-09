public HMM<O> learn(int[][] observations, int iterations) {
        HMM<O> hmm = this;

        for (int iter = 0; iter < iterations; iter++) {
            hmm = hmm.iterate(observations);
        }

        return hmm;
    }