public Month firstMonth() {
        switch (this) {
            case Q1: return Month.JANUARY;
            case Q2: return Month.APRIL;
            case Q3: return Month.JULY;
            case Q4: return Month.OCTOBER;
            default: throw new IllegalStateException("Unreachable");
        }
    }