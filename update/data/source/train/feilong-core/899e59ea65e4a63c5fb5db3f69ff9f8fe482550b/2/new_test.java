@Test
    public void testGetResetTodayAndTomorrow(){
        assertArrayEquals(
                        ConvertUtil.toArray(DateUtil.getFirstDateOfThisDay(NOW), DateUtil.getFirstDateOfThisDay(DateUtil.addDay(NOW, 1))),
                        DateExtensionUtil.getResetTodayAndTomorrow());
    }