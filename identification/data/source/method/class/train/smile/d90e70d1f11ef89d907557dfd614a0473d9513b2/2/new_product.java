public int[] predict(int[] o) {
        int N = a.nrows();
        // The porbability of the most probable path.
        double[][] trellis = new double[o.length][N];
        // Backtrace.
        int[][] psy = new int[o.length][N];
        // The most likely state sequence.
        int[] s = new int[o.length];

        // forward
        for (int i = 0; i < N; i++) {
            trellis[0][i] = MathEx.log(pi[i]) + MathEx.log(b.get(i, o[0]));
            psy[0][i] = 0;
        }

        for (int t = 1; t < o.length; t++) {
            for (int j = 0; j < N; j++) {
                double maxDelta = Double.NEGATIVE_INFINITY;
                int maxPsy = 0;

                for (int i = 0; i < N; i++) {
                    double delta = trellis[t - 1][i] + MathEx.log(a.get(i, j));

                    if (maxDelta < delta) {
                        maxDelta = delta;
                        maxPsy = i;
                    }
                }

                trellis[t][j] = maxDelta + MathEx.log(b.get(j, o[t]));
                psy[t][j] = maxPsy;
            }
        }

        // trace back
        int n = o.length - 1;
        double maxDelta = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < N; i++) {
            if (maxDelta < trellis[n][i]) {
                maxDelta = trellis[n][i];
                s[n] = i;
            }
        }

        for (int t = n; t-- > 0;) {
            s[t] = psy[t + 1][s[t + 1]];
        }

        return s;
    }