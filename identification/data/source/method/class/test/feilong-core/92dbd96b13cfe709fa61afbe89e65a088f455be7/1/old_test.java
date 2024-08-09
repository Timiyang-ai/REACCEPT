@Test(expected = NullPointerException.class)
    public void testFormatDuration2333(){
        formatDuration(null, new Date());
    }