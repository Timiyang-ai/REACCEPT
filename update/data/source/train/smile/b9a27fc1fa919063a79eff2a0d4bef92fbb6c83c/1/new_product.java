void learn(T[] x, int[] y, double[] weight) {
            if (p == 0 && kernel instanceof LinearKernel) {
                if (x instanceof double[][]) {
                    double[] x0 = (double[]) x[0];
                    p = x0.length;
                } else if (x instanceof float[][]) {
                    float[] x0 = (float[]) x[0];
                    p = x0.length;
                } else {
                    throw new UnsupportedOperationException("Unsupported data type for linear kernel.");
                }
            }

            int c1 = 0, c2 = 0;
            for (SupportVector v : sv) {
                if (v != null) {
                    if (v.y > 0) c1++;
                    else if (v.y < 0) c2++;
                }
            }
            
            // If the SVM is empty or has very few support vectors, use some
            // instances as initial support vectors.
            final int n = x.length;
            if (c1 < 5 || c2 < 5) {
                for (int i = 0; i < n; i++) {
                    if (y[i] == 1 && c1 < 5) {
                        if (weight == null) {
                            process(x[i], y[i]);
                        } else {
                            process(x[i], y[i], weight[i]);
                        }
                        c1++;
                    }
                    if (y[i] == -1 && c2 < 5) {
                        if (weight == null) {
                            process(x[i], y[i]);
                        } else {
                            process(x[i], y[i], weight[i]);
                        }
                        c2++;
                    }
                    if (c1 >= 5 && c2 >= 5) {
                        break;
                    }
                }
            }

            // train SVM in a stochastic order.
            int[] index = MathEx.permutate(n);
            for (int i = 0; i < n; i++) {
                if (weight == null) {
                    process(x[index[i]], y[index[i]]);
                } else {
                    process(x[index[i]], y[index[i]], weight[index[i]]);                    
                }

                do {
                    reprocess(tol); // at least one call to reprocess
                    minmax();
                } while (gmax - gmin > 1000);
            }
        }