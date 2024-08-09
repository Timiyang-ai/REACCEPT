@Test
    public void addYear(){
        logDate(DateUtil.addYear(NOW, 5));
        logDate(DateUtil.addYear(NOW, -5));
    }