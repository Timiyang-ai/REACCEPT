public static double logSumExact(double... logValues) {
        double result = LOG_ZERO;
        for (double logValue : logValues) {
            result = logSumExact(result, logValue);
        }
        return result;
    }