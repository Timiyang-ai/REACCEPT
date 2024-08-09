    @Test
    public void isTel() {
        assertTrue(RegexUtils.isTel("033-88888888"));
        assertTrue(RegexUtils.isTel("033-7777777"));
        assertTrue(RegexUtils.isTel("0444-88888888"));
        assertTrue(RegexUtils.isTel("0444-7777777"));
        assertTrue(RegexUtils.isTel("033 88888888"));
        assertTrue(RegexUtils.isTel("033 7777777"));
        assertTrue(RegexUtils.isTel("0444 88888888"));
        assertTrue(RegexUtils.isTel("0444 7777777"));
        assertTrue(RegexUtils.isTel("03388888888"));
        assertTrue(RegexUtils.isTel("0337777777"));
        assertTrue(RegexUtils.isTel("044488888888"));
        assertTrue(RegexUtils.isTel("04447777777"));

        assertFalse(RegexUtils.isTel("133-88888888"));
        assertFalse(RegexUtils.isTel("033-666666"));
        assertFalse(RegexUtils.isTel("0444-999999999"));
    }