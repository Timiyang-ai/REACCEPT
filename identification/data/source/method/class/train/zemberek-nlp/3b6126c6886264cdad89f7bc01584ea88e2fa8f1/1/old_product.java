public static float linearToLog(float linear) {
        if (linear == 0)
            return LOG_ZERO_FLOAT;
        return (float) Math.log(linear);
    }