@Test
    public void test_withChrono() {
        DateTimeFormatter test = fmt;
        assertEquals(test.getChrono(), null);
        test = test.withChrono(ISOChrono.INSTANCE);
        assertEquals(test.getChrono(), ISOChrono.INSTANCE);
        test = test.withChrono(null);
        assertEquals(test.getChrono(), null);
    }