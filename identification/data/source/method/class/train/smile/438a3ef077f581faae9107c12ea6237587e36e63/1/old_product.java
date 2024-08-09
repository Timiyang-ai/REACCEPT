public static double min(DifferentiableMultivariateFunction func, double[] x, double gtol, int maxIter) {
        // The convergence criterion on x values.
        final double TOLX = 4 * EPSILON;
        // The scaled maximum step length allowed in line searches.
        final double STPMX = 100.0;

        double den, fac, fad, fae, sumdg, sumxi, temp, test;

        int n = x.length;
        double[] dg = new double[n];
        double[] g = new double[n];
        double[] hdg = new double[n];
        double[] xnew = new double[n];
        double[] xi = new double[n];
        double[][] hessin = new double[n][n];

        // Calculate starting function value and gradient and initialize the
        // inverse Hessian to the unit matrix.
        double f = func.f(x, g);
        logger.info(String.format("BFGS: initial function value: %.5g", f));

        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            hessin[i][i] = 1.0;
            // Initialize line direction.
            xi[i] = -g[i];
            sum += x[i] * x[i];
        }

        double stpmax = STPMX * Math.max(Math.sqrt(sum), n);

        for (int iter = 1; iter <= maxIter; iter++) {
            // The new function evaluation occurs in line search.
            f = linesearch(func, x, f, g, xi, xnew, stpmax);

            if (iter % 10 == 0) {
                logger.info(String.format("BFGS: the function value after %3d iterations: %.5g", iter, f));
            }

            // update the line direction and current point.
            for (int i = 0; i < n; i++) {
                xi[i] = xnew[i] - x[i];
                x[i] = xnew[i];
            }

            // Test for convergence on x.
            test = 0.0;
            for (int i = 0; i < n; i++) {
                temp = Math.abs(xi[i]) / Math.max(Math.abs(x[i]), 1.0);
                if (temp > test) {
                    test = temp;
                }
            }

            if (test < TOLX) {
                logger.info(String.format("BFGS: the function value after %3d iterations: %.5g", iter, f));
                return f;
            }
            
            System.arraycopy(g, 0, dg, 0, n);

            func.f(x, g);

            // Test for convergence on zero gradient.
            den = Math.max(f, 1.0);
            test = 0.0;
            for (int i = 0; i < n; i++) {
                temp = Math.abs(g[i]) * Math.max(Math.abs(x[i]), 1.0) / den;
                if (temp > test) {
                    test = temp;
                }
            }

            if (test < gtol) {
                logger.info(String.format("BFGS: the function value after %3d iterations: %.5g", iter, f));
                return f;
            }

            for (int i = 0; i < n; i++) {
                dg[i] = g[i] - dg[i];
            }

            for (int i = 0; i < n; i++) {
                hdg[i] = 0.0;
                for (int j = 0; j < n; j++) {
                    hdg[i] += hessin[i][j] * dg[j];
                }
            }

            fac = fae = sumdg = sumxi = 0.0;
            for (int i = 0; i < n; i++) {
                fac += dg[i] * xi[i];
                fae += dg[i] * hdg[i];
                sumdg += dg[i] * dg[i];
                sumxi += xi[i] * xi[i];
            }

            // Skip upudate if fac is not sufficiently positive.
            if (fac > Math.sqrt(EPSILON * sumdg * sumxi)) {
                fac = 1.0 / fac;
                fad = 1.0 / fae;

                // The vector that makes BFGS different from DFP.
                for (int i = 0; i < n; i++) {
                    dg[i] = fac * xi[i] - fad * hdg[i];
                }

                // BFGS updating formula.
                for (int i = 0; i < n; i++) {
                    for (int j = i; j < n; j++) {
                        hessin[i][j] += fac * xi[i] * xi[j] - fad * hdg[i] * hdg[j] + fae * dg[i] * dg[j];
                        hessin[j][i] = hessin[i][j];
                    }
                }
            }

            // Calculate the next direction to go.
            Arrays.fill(xi, 0.0);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    xi[i] -= hessin[i][j] * g[j];
                }
            }
        }

        throw new IllegalStateException("BFGS: Too many iterations.");
    }