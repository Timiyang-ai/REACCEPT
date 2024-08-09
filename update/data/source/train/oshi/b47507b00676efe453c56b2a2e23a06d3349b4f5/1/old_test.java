@Test
    public void testFormatHertz() {
        assertEquals("0 Hz", FormatUtil.formatHertz(0));
        assertEquals("1 Hz", FormatUtil.formatHertz(1));
        assertEquals("999 Hz", FormatUtil.formatHertz(999));
        assertEquals("1 kHz", FormatUtil.formatHertz(1000));
        assertEquals("1 MHz", FormatUtil.formatHertz(1000 * 1000));
        assertEquals("1 GHz", FormatUtil.formatHertz(1000 * 1000 * 1000));
        assertEquals("1 THz", FormatUtil.formatHertz(1000L * 1000L * 1000L * 1000L));
    }