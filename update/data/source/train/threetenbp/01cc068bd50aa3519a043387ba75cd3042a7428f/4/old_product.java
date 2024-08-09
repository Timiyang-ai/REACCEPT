public MonthOfYear getFirstMonthOfQuarter() {
        switch (this) {
            case Q1: return MonthOfYear.JANUARY;
            case Q2: return MonthOfYear.APRIL;
            case Q3: return MonthOfYear.JULY;
            case Q4: return MonthOfYear.OCTOBER;
            default: throw new IllegalStateException("Unreachable");
        }
    }