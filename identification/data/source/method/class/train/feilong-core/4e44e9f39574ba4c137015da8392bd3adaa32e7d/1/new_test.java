@Test
    public void testFormat2(){
        assertEquals("26", NumberFormatUtil.format(25.5, "#####", RoundingMode.HALF_UP));
        assertEquals("RP 26", NumberFormatUtil.format(25.5, "RP #####", RoundingMode.HALF_UP));
    }