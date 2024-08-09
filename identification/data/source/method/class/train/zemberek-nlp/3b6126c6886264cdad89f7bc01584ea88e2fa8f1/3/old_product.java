public static float logSum(float... logValues) {
        float result = LOG_ZERO_FLOAT;
        for (float logValue : logValues) {
            result = logSum(result, logValue);
        }
        return result;
    }