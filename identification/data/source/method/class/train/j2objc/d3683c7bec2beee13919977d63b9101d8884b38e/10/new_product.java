public static double copySign(double magnitude, double sign) {
        return Math.copySign(magnitude, (Double.isNaN(sign)?1.0d:sign));
    }