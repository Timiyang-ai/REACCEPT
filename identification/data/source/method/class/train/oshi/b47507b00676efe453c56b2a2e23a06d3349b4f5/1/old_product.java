public static String formatHertz(long hertz) {
        if (hertz < KILO) { // Hz
            return String.format("%d Hz", hertz);
        } else if (hertz < MEGA && hertz % KILO == 0) { // KHz
            return String.format("%.0f kHz", (double) hertz / KILO);
        } else if (hertz < MEGA) {
            return String.format("%.1f kHz", (double) hertz / KILO);
        } else if (hertz < GIGA && hertz % MEGA == 0) { // MHz
            return String.format("%.0f MHz", (double) hertz / MEGA);
        } else if (hertz < GIGA) {
            return String.format("%.1f MHz", (double) hertz / MEGA);
        } else if (hertz < TERA && hertz % GIGA == 0) { // GHz
            return String.format("%.0f GHz", (double) hertz / GIGA);
        } else if (hertz < TERA) {
            return String.format("%.1f GHz", (double) hertz / GIGA);
        } else if (hertz < PETA && hertz % TERA == 0) { // THz
            return String.format("%.0f THz", (double) hertz / TERA);
        } else if (hertz < PETA) {
            return String.format("%.1f THz", (double) hertz / TERA);
        } else if (hertz < EXA && hertz % PETA == 0) { // EHz
            return String.format("%.0f EHz", (double) hertz / PETA);
        } else if (hertz < EXA) {
            return String.format("%.1f EHz", (double) hertz / PETA);
        } else {
            return String.format("%d Hz", hertz);
        }
    }