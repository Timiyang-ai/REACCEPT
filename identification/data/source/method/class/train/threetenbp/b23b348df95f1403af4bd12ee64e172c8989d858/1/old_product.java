@Override
    public long toEpochDay() {
         return getGregorianEpochDay(yearOfEra, monthOfYear, dayOfMonth);
    }