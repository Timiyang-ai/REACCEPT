public float lookup(float... logValues) {
            float result = LOG_ZERO_FLOAT;
            for (float logValue : logValues) {
                result = lookup(result, logValue);
            }
            return result;
        }