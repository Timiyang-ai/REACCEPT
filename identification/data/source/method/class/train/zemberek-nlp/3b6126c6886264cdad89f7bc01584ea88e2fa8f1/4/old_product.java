public static float logSum(float logA, float logB) {
        if (logA > logB) {
            final float dif = logA - logB; // logA-logB because during lookup calculation dif is multiplied with -1
            return dif >= 10d ? logA : logA + logSumLookupFloat[(int) (dif * SCALE_FLOAT)];
        } else {
            final float dif = logB - logA;
            return dif >= 10d ? logB : logB + logSumLookupFloat[(int) (dif * SCALE_FLOAT)];
        }
    }