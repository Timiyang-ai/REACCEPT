double EM(List<Component> components, int[] x , double gamma, int maxIter) {
        if (x.length < components.size() / 2)
                throw new IllegalArgumentException("Too many components");

        if (gamma < 0.0 || gamma > 0.2)
            throw new IllegalArgumentException("Invalid regularization factor gamma.");

        if (maxIter <= 0)
            maxIter = Integer.MAX_VALUE;

        int n = x.length;
        int m = components.size();

        double[][] posteriori = new double[m][n];

        // Log Likelihood
        double L = 0.0;
        for (double xi : x) {
            double p = 0.0;
            for (Component c : components)
                p += c.priori * c.distribution.p(xi);
            if (p > 0) L += Math.log(p);
        }

        // EM loop until convergence
        int iter = 0;
        for (; iter < maxIter; iter++) {

            // Expectation step
            for (int i = 0; i < m; i++) {
                Component c = components.get(i);

                for (int j = 0; j < n; j++) {
                    posteriori[i][j] = c.priori * c.distribution.p(x[j]);
                }
            }

            // Normalize posteriori probability.
            for (int j = 0; j < n; j++) {
                double p = 0.0;

                for (int i = 0; i < m; i++) {
                    p += posteriori[i][j];
                }

                for (int i = 0; i < m; i++) {
                    posteriori[i][j] /= p;
                }

                // Adjust posterior probabilites based on Regularized EM algorithm.
                if (gamma > 0) {
                    for (int i = 0; i < m; i++) {
                        posteriori[i][j] *= (1 + gamma * Math.log2(posteriori[i][j]));
                        if (Double.isNaN(posteriori[i][j]) || posteriori[i][j] < 0.0) {
                            posteriori[i][j] = 0.0;
                        }
                    }
                }
            }

            // Maximization step
            List<Component> newConfig = new ArrayList<>();
            for (int i = 0; i < m; i++)
                newConfig.add(((DiscreteExponentialFamily) components.get(i).distribution).M(x, posteriori[i]));

            double sumAlpha = 0.0;
            for (int i = 0; i < m; i++)
                sumAlpha += newConfig.get(i).priori;

            for (int i = 0; i < m; i++)
                newConfig.get(i).priori /= sumAlpha;

            double newL = 0.0;
            for (double xi : x) {
                double p = 0.0;
                for (Component c : newConfig) {
                    p += c.priori * c.distribution.p(xi);
                }
                if (p > 0) newL += Math.log(p);
            }

            if (newL > L) {
                L = newL;
                components.clear();
                components.addAll(newConfig);
            } else {
                break;
            }
        }

        return L;
    }