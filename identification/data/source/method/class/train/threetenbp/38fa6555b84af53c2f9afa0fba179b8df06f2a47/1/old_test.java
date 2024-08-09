@Test(expectedExceptions=NullPointerException.class)
    public void factory_date_objects_nullYear() {
        OffsetDate.date(null, MonthOfYear.JULY, DayOfMonth.dayOfMonth(15), OFFSET_PONE);
    }