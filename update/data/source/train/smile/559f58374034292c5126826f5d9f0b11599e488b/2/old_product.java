public void learn(double[][] x, int[] y) {
        int n = x.length;
        int[] index = Math.permutate(n);
        for (int i = 0; i < n; i++) {
            learn(x[index[i]], y[index[i]]);
        }
    }