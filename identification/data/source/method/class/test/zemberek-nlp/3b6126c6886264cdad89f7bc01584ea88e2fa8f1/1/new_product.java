public double lookup(double... logValues) {
            double result = LOG_ZERO;
            for (double logValue : logValues) {
                result = lookup(result, logValue);
            }
            return result;
        }