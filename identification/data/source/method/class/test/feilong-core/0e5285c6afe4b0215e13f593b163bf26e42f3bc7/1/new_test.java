@Test
    public void addYear(){
        logDate(DateUtil.addYear(NOW, 5));
        logDate(NOW);
        logDate(DateUtils.addYears(NOW, 5));
        logDate(NOW);
        logDate(DateUtil.addYear(NOW, -5));
        logDate(NOW);
    }