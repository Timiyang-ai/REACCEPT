public static double lgamma(double x) {
        double xcopy = x;
        double fg = 0.0;
        double first = x + LANCZOS_SMALL_GAMMA + 0.5;
        double second = LANCZOS_COEFF[0];

        if (x >= 0.0) {
            if (x >= 1.0 && x - (int) x == 0.0) {
                fg = Math.logFactorial((int) x - 1);
            } else {
                first -= (x + 0.5) * Math.log(first);
                for (int i = 1; i <= LANCZOS_N; i++) {
                    second += LANCZOS_COEFF[i] / ++xcopy;
                }
                fg = Math.log(Math.sqrt(2.0 * Math.PI) * second / x) - first;
            }
        } else {
            fg = Math.PI / (gamma(1.0 - x) * Math.sin(Math.PI * x));

            if (fg != 1.0 / 0.0 && fg != -1.0 / 0.0) {
                if (fg < 0) {
                    throw new IllegalArgumentException("The gamma function is negative");
                } else {
                    fg = Math.log(fg);
                }
            }
        }
        return fg;
    }