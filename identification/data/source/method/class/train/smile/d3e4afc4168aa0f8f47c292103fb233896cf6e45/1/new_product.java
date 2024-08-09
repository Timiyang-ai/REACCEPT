@Override
    public int predict(double[] x, double[] posteriori) {
        Arrays.fill(posteriori, 0.0);

        for (int i = 0; i < trees.length; i++) {
            posteriori[trees[i].predict(x)] += alpha[i];
        }

        double sum = Math.sum(posteriori);
        for (int i = 0; i < k; i++) {
            posteriori[i] /= sum;
        }

        return Math.whichMax(posteriori);
    }