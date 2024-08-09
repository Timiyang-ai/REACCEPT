public static void LABToXYZ(@FloatRange(from = 0f, to = 100) final double l,
            @FloatRange(from = -128, to = 127) final double a,
            @FloatRange(from = -128, to = 127) final double b,
            @NonNull double[] outXyz) {
        final double fy = (l + 16) / 116;
        final double fx = a / 500 + fy;
        final double fz = fy - b / 200;

        double tmp = Math.pow(fx, 3);
        final double xr = tmp > XYZ_EPSILON ? tmp : (116 * fx - 16) / XYZ_KAPPA;
        final double yr = l > XYZ_KAPPA * XYZ_EPSILON ? Math.pow(fy, 3) : l / XYZ_KAPPA;

        tmp = Math.pow(fz, 3);
        final double zr = tmp > XYZ_EPSILON ? tmp : (116 * fz - 16) / XYZ_KAPPA;

        outXyz[0] = xr * XYZ_WHITE_REFERENCE_X;
        outXyz[1] = yr * XYZ_WHITE_REFERENCE_Y;
        outXyz[2] = zr * XYZ_WHITE_REFERENCE_Z;
    }