public void update(int[][] observations, int iterations) {
        for (int iter = 0; iter < iterations; iter++) {
            iterate(observations);
        }
    }