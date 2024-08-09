@Test
    public void testGenerateXYZ() {
        System.out.println("generateXYZ");
        String result;

        result = GcodeUtils.generateXYZ("G0", UnitUtils.Units.INCH, "10", "11", 1, 1, 1);
        assertEquals("G20G0X10Y10Z10F11", result);

        result = GcodeUtils.generateXYZ("G0", UnitUtils.Units.MM, "10", "11", 1, 1, 1);
        assertEquals("G21G0X10Y10Z10F11", result);

        result = GcodeUtils.generateXYZ("G91G0", UnitUtils.Units.MM, "10", "11", 1, 0, 0);
        assertEquals("G21G91G0X10F11", result);

        result = GcodeUtils.generateXYZ("G91G0", UnitUtils.Units.MM, "10", "11", 0, -1, -1);
        assertEquals("G21G91G0Y-10Z-10F11", result);
    }