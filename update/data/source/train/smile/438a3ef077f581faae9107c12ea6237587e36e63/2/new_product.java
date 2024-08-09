public static double root(Function func, double x1, double x2, double tol, int maxIter) {
        if (tol <= 0.0) {
            throw new IllegalArgumentException("Invalid tolerance: " + tol);            
        }
        
        if (maxIter <= 0) {
            throw new IllegalArgumentException("Invalid maximum number of iterations: " + maxIter);            
        }
        
        double a = x1, b = x2, c = x2, d = 0, e = 0, fa = func.apply(a), fb = func.apply(b), fc, p, q, r, s, xm;
        if ((fa > 0.0 && fb > 0.0) || (fa < 0.0 && fb < 0.0)) {
            throw new IllegalArgumentException("Root must be bracketed.");
        }

        fc = fb;
        for (int iter = 1; iter <= maxIter; iter++) {
            if ((fb > 0.0 && fc > 0.0) || (fb < 0.0 && fc < 0.0)) {
                c = a;
                fc = fa;
                e = d = b - a;
            }

            if (Math.abs(fc) < Math.abs(fb)) {
                a = b;
                b = c;
                c = a;
                fa = fb;
                fb = fc;
                fc = fa;
            }

            tol = 2.0 * EPSILON * Math.abs(b) + 0.5 * tol;
            xm = 0.5 * (c - b);

            if (iter % 10 == 0) {
                logger.info(String.format("Brent: the root after %3d iterations: %.5g, error = %.5g", iter, b, xm));
            }

            if (Math.abs(xm) <= tol || fb == 0.0) {
                logger.info(String.format("Brent: the root after %3d iterations: %.5g, error = %.5g", iter, b, xm));
                return b;
            }

            if (Math.abs(e) >= tol && Math.abs(fa) > Math.abs(fb)) {
                s = fb / fa;
                if (a == c) {
                    p = 2.0 * xm * s;
                    q = 1.0 - s;
                } else {
                    q = fa / fc;
                    r = fb / fc;
                    p = s * (2.0 * xm * q * (q - r) - (b - a) * (r - 1.0));
                    q = (q - 1.0) * (r - 1.0) * (s - 1.0);
                }

                if (p > 0.0) {
                    q = -q;
                }

                p = Math.abs(p);
                double min1 = 3.0 * xm * q - Math.abs(tol * q);
                double min2 = Math.abs(e * q);
                if (2.0 * p < (min1 < min2 ? min1 : min2)) {
                    e = d;
                    d = p / q;
                } else {
                    d = xm;
                    e = d;
                }
            } else {
                d = xm;
                e = d;
            }

            a = b;
            fa = fb;
            if (Math.abs(d) > tol) {
                b += d;
            } else {
                b += Math.copySign(tol, xm);
            }
            fb = func.apply(b);
        }

        logger.error("Brent's method exceeded the maximum number of iterations.");
        return b;
    }