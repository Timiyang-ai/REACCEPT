public long toTotalMonths() {
        return years * 12L + months;  // no overflow
    }