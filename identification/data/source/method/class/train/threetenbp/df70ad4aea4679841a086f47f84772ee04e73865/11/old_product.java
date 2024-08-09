public HijrahDate plusYears(int years) {
        if (years == 0) {
            return this;
        }
        int newYear = 0;
        try {
            newYear = MathUtils.safeAdd(this.yearOfEra, years);
            return HijrahDate.hijrahDate(this.era, newYear, this.monthOfYear, this.dayOfMonth);
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