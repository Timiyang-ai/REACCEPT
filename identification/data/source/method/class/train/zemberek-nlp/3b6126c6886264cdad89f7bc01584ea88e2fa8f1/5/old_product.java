public static double logSum(double logA, double logB) {
        if (logA > logB) {
            final double dif = logA - logB; // logA-logB because during lookup calculation dif is multiplied with -1
            return dif >= 30d ? logA : logA + logSumLookup[(int) (dif * SCALE)];
        } else {
            final double dif = logB - logA;
            return dif >= 30d ? logB : logB + logSumLookup[(int) (dif * SCALE)];
        }
    }