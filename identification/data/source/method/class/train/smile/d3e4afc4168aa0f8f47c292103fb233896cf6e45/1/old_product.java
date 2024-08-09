@Override
    public int predict(double[] x, double[] posteriori) {
        if (k == 2) {
            double y = 0.0;
            for (int i = 0; i < trees.length; i++) {
                double yi = alpha[i] * trees[i].predict(x);
                if (yi > 0.0) posteriori[1] += yi; else posteriori[0] += yi;
                y += yi;
            }

            double sum = Math.abs(posteriori[0]) + posteriori[1];
            for (int i = 0; i < k; i++) posteriori[i] /= sum;
            return y > 0 ? 1 : 0;
        } else {
            for (int i = 0; i < trees.length; i++) {
                posteriori[trees[i].predict(x)] += alpha[i];
            }

            for (int i = 0; i < k; i++) posteriori[i] /= Math.sum(posteriori);
            return Math.whichMax(posteriori);
        }
    }