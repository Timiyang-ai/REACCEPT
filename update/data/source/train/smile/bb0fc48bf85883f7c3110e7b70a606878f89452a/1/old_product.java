public double learn(double[] x) {
        if (x.length != n) {
            throw new IllegalArgumentException(String.format("Invalid input vector size: %d, expected: %d", x.length, n));
        }

        Math.ax(projection, x, y);

        for (int j = 0; j < p; j++) {
            for (int i = 0; i < n; i++) {
                double delta = x[i];
                for (int l = 0; l <= j; l++) {
                    delta -= projection[l][i] * y[l];
                }
                projection[j][i] += r * y[j] * delta;

                if (Double.isInfinite(projection[j][i])) {
                    throw new IllegalStateException("GHA lost convergence. Lower learning rate?");
                }
            }
        }

        Math.ax(projection, x, y);
        Math.atx(projection, y, wy);
        return Math.squaredDistance(x, wy);
    }