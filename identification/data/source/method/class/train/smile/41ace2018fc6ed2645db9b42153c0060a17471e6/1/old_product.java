public void learn(int iterations) {
        double[][] Y = coordinates;
        int n = Y.length;
        int d = Y[0].length;

        int nprocs = MulticoreExecutor.getThreadPoolSize();
        int chunk = n / nprocs;
        List<SNETask> tasks = new ArrayList<>();
        for (int i = 0; i < nprocs; i++) {
            int start = i * chunk;
            int end = i == nprocs-1 ? n : (i+1) * chunk;
            SNETask task = new SNETask(start, end);
            tasks.add(task);
        }

        for (int iter = 1; iter <= iterations; iter++, totalIter++) {
            MathEx.pdist(Y, Q, true, false);
            Qsum = 0.0;
            for (int i = 0; i < n; i++) {
                double[] Qi = Q[i];
                for (int j = 0; j < i; j++) {
                    double q = 1.0 / (1.0 + Qi[j]);
                    Qi[j] = q;
                    Q[j][i] = q;
                    Qsum += q;
                }
            }
            Qsum *= 2.0;

            try {
                MulticoreExecutor.run(tasks);
            } catch (Exception e) {
                logger.error("t-SNE iteration task fails: {}", e);
            }

            if (totalIter == momentumSwitchIter) {
                momentum = finalMomentum;
                for (int i = 0; i < n; i++) {
                    double[] Pi = P[i];
                    for (int j = 0; j < n; j++) {
                        Pi[j] /= 12.0;
                    }
                }
            }

            // Compute current value of cost function
            if (iter % 50 == 0)   {
                double C = 0.0;
                for (int i = 0; i < n; i++) {
                    double[] Pi = P[i];
                    double[] Qi = Q[i];
                    for (int j = 0; j < i; j++) {
                        double p = Pi[j];
                        double q = Qi[j] / Qsum;
                        if (Double.isNaN(q) || q < 1E-16) q = 1E-16;
                        C += p * MathEx.log2(p / q);
                    }
                }
                logger.info("Error after {} iterations: {}", totalIter, 2 * C);
            }
        }

        // Make solution zero-mean
        double[] colMeans = MathEx.colMeans(Y);
        for (int i = 0; i < n; i++) {
            double[] Yi = Y[i];
            for (int j = 0; j < d; j++) {
                Yi[j] -= colMeans[j];
            }
        }
    }