public static float copySign(float magnitude, float sign) {
        return Math.copySign(magnitude, (Float.isNaN(sign)?1.0f:sign));
    }