public static double root(DifferentiableFunction func, double x1, double x2, double tol, int maxIter) {
        if (tol <= 0.0) {
            throw new IllegalArgumentException("Invalid tolerance: " + tol);            
        }
        
        if (maxIter <= 0) {
            throw new IllegalArgumentException("Invalid maximum number of iterations: " + maxIter);            
        }
        
        double fl = func.apply(x1);
        double fh = func.apply(x2);
        if ((fl > 0.0 && fh > 0.0) || (fl < 0.0 && fh < 0.0)) {
            throw new IllegalArgumentException("Root must be bracketed in rtsafe");
        }

        if (fl == 0.0) {
            return x1;
        }
        if (fh == 0.0) {
            return x2;
        }

        double xh, xl;
        if (fl < 0.0) {
            xl = x1;
            xh = x2;
        } else {
            xh = x1;
            xl = x2;
        }
        double rts = 0.5 * (x1 + x2);
        double dxold = Math.abs(x2 - x1);
        double dx = dxold;
        double f = func.apply(rts);
        double df = func.g(rts);
        for (int iter = 1; iter <= maxIter; iter++) {
            if ((((rts - xh) * df - f) * ((rts - xl) * df - f) > 0.0) || (Math.abs(2.0 * f) > Math.abs(dxold * df))) {
                dxold = dx;
                dx = 0.5 * (xh - xl);
                rts = xl + dx;
                if (xl == rts) {
                    logger.info(String.format("Newton-Raphson: the root after %3d iterations: %.5g, error = %.5g", iter, rts, dx));
                    return rts;
                }
            } else {
                dxold = dx;
                dx = f / df;
                double temp = rts;
                rts -= dx;
                if (temp == rts) {
                    logger.info(String.format("Newton-Raphson: the root after %3d iterations: %.5g, error = %.5g", iter, rts, dx));
                    return rts;
                }
            }

            if (iter % 10 == 0) {
                logger.info(String.format("Newton-Raphson: the root after %3d iterations: %.5g, error = %.5g", iter, rts, dx));
            }

            if (Math.abs(dx) < tol) {
                logger.info(String.format("Newton-Raphson: the root after %3d iterations: %.5g, error = %.5g", iter, rts, dx));
                return rts;
            }

            f = func.apply(rts);
            df = func.g(rts);
            if (f < 0.0) {
                xl = rts;
            } else {
                xh = rts;
            }
        }

        logger.error("Newton-Raphson method exceeded the maximum number of iterations.");
        return rts;
    }