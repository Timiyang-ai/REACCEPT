@Test(expected = NullPointerException.class)
    public void testFormat2(){
        NumberFormatUtil.format(25, null);
    }