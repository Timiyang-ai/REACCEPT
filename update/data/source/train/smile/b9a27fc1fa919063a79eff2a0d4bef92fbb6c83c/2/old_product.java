@SuppressWarnings("unchecked")
    public void learn(T[] x, int[] y, double[] weight) {
        if (x.length != y.length) {
            throw new IllegalArgumentException(String.format("The sizes of X and Y don't match: %d != %d", x.length, y.length));
        }

        if (weight != null && x.length != weight.length) {
            throw new IllegalArgumentException(String.format("The sizes of X and instance weight don't match: %d != %d", x.length, weight.length));
        }

        int miny = Math.min(y);
        if (miny < 0) {
            throw new IllegalArgumentException("Negative class label:" + miny);
        }

        int maxy = Math.max(y);
        if (maxy >= k) {
            throw new IllegalArgumentException("Invalid class label:" + maxy);
        }

        if (k == 2) {
            int[] yi = new int[y.length];
            for (int i = 0; i < y.length; i++) {
                if (y[i] == 1) {
                    yi[i] = +1;
                } else {
                    yi[i] = -1;
                }
            }
            
            if (weight == null) {
                svm.learn(x, yi);
            } else {
                svm.learn(x, yi, weight);
            }
        } else if (strategy == Multiclass.ONE_VS_ALL) {
            List<TrainingTask> tasks = new ArrayList<TrainingTask>(k);
            for (int i = 0; i < k; i++) {
                int[] yi = new int[y.length];
                double[] w = wi == null ? weight : new double[y.length];
                for (int l = 0; l < y.length; l++) {
                    if (y[l] == i) {
                        yi[l] = +1;
                    } else {
                        yi[l] = -1;
                    }
                    
                    if (wi != null) {
                        w[l] = wi[y[l]];
                        if (weight != null) {
                            w[l] *= weight[l];
                        }
                    }
                }

                tasks.add(new TrainingTask(svms.get(i), x, yi, w));
            }

            try {
                MulticoreExecutor.run(tasks);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } else {
            List<TrainingTask> tasks = new ArrayList<TrainingTask>(k * (k - 1) / 2);
            for (int i = 0, m = 0; i < k; i++) {
                for (int j = i + 1; j < k; j++, m++) {
                    int n = 0;
                    for (int l = 0; l < y.length; l++) {
                        if (y[l] == i || y[l] == j) {
                            n++;
                        }
                    }

                    T[] xij = (T[]) java.lang.reflect.Array.newInstance(x.getClass().getComponentType(), n);
                    int[] yij = new int[n];
                    double[] wij = weight == null ? null : new double[n];

                    for (int l = 0, q = 0; l < y.length; l++) {
                        if (y[l] == i) {
                            xij[q] = x[l];
                            yij[q] = +1;
                            if (weight != null) {
                                wij[q] = weight[l];                                
                            }
                            q++;
                        } else if (y[l] == j) {
                            xij[q] = x[l];
                            yij[q] = -1;
                            if (weight != null) {
                                wij[q] = weight[l];                                
                            }
                            q++;
                        }
                    }

                    tasks.add(new TrainingTask(svms.get(m), xij, yij, wij));
                }
            }

            try {
                MulticoreExecutor.run(tasks);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }