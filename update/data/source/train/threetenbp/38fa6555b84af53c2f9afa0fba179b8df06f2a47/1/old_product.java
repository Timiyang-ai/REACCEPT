@Test(expectedExceptions=NullPointerException.class)
    public void factory_date_objects_nullYear() {
        LocalDate.date(null, MonthOfYear.JULY, DayOfMonth.dayOfMonth(15));
    }