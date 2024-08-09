@Test(groups={"tck"})
    public void test_minLength() {
        assertEquals(Month.JANUARY.minLength(), 31);
        assertEquals(Month.FEBRUARY.minLength(), 28);
        assertEquals(Month.MARCH.minLength(), 31);
        assertEquals(Month.APRIL.minLength(), 30);
        assertEquals(Month.MAY.minLength(), 31);
        assertEquals(Month.JUNE.minLength(), 30);
        assertEquals(Month.JULY.minLength(), 31);
        assertEquals(Month.AUGUST.minLength(), 31);
        assertEquals(Month.SEPTEMBER.minLength(), 30);
        assertEquals(Month.OCTOBER.minLength(), 31);
        assertEquals(Month.NOVEMBER.minLength(), 30);
        assertEquals(Month.DECEMBER.minLength(), 31);
    }