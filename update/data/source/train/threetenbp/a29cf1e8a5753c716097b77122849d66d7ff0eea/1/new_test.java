@Test(groups={"tck"})
    public void test_maxLength() {
        assertEquals(Month.JANUARY.maxLength(), 31);
        assertEquals(Month.FEBRUARY.maxLength(), 29);
        assertEquals(Month.MARCH.maxLength(), 31);
        assertEquals(Month.APRIL.maxLength(), 30);
        assertEquals(Month.MAY.maxLength(), 31);
        assertEquals(Month.JUNE.maxLength(), 30);
        assertEquals(Month.JULY.maxLength(), 31);
        assertEquals(Month.AUGUST.maxLength(), 31);
        assertEquals(Month.SEPTEMBER.maxLength(), 30);
        assertEquals(Month.OCTOBER.maxLength(), 31);
        assertEquals(Month.NOVEMBER.maxLength(), 30);
        assertEquals(Month.DECEMBER.maxLength(), 31);
    }