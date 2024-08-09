public HijrahDate minusYears(int years) {
        if (years == 0) {
            return this;
        }
        int newYear = 0;
        try {
            newYear = MathUtils.safeSubtract(this.yearOfEra, years);
            return HijrahDate.of(this.era, newYear, this.monthOfYear, this.dayOfMonth);
        } catch (ArithmeticException ae) {
            throw new CalendricalException("Year "
                    + (((long) this.yearOfEra) + years)
                    + " exceeds the supported year range");
        } catch (IllegalCalendarFieldValueException ae) {
            throw new CalendricalException("Year "
                    + (((long) this.yearOfEra) + years)
                    + " exceeds the supported year range");
        }
    }