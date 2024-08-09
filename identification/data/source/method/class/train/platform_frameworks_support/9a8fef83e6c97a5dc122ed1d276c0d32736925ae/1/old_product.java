@ColorInt
    public static int LABToColor(@FloatRange(from = 0f, to = 100) final double l,
            @FloatRange(from = -128, to = 127) final double a,
            @FloatRange(from = -128, to = 127) final double b) {
        final double[] result = new double[3];
        LABToXYZ(l, a, b, result);
        return XYZToColor(result[0], result[1], result[2]);
    }