public double learn(double[] x) {
        if (x.length != n) {
            throw new IllegalArgumentException(String.format("Invalid input vector size: %d, expected: %d", x.length, n));
        }

        projection.ax(x, y);

        for (int j = 0; j < p; j++) {
            for (int i = 0; i < n; i++) {
                double delta = x[i];
                for (int l = 0; l <= j; l++) {
                    delta -= projection.get(l, i) * y[l];
                }
                projection.add(j, i, r * y[j] * delta);

                if (Double.isInfinite(projection.get(j, i))) {
                    throw new IllegalStateException("GHA lost convergence. Lower learning rate?");
                }
            }
        }

        projection.ax(x, y);
        projection.atx(y, wy);
        return MathEx.squaredDistance(x, wy);
    }