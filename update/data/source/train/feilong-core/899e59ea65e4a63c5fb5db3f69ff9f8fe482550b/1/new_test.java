@Test
    public void testGetResetYesterdayAndToday(){
        assertArrayEquals(
                        ConvertUtil.toArray(DateUtil.getFirstDateOfThisDay(DateUtil.addDay(NOW, -1)), DateUtil.getFirstDateOfThisDay(NOW)),
                        DateExtensionUtil.getResetYesterdayAndToday());
    }