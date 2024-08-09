    @Test
    public void generateMoveCommand() {
        String result;

        result = GcodeUtils.generateMoveCommand("G0", 10, 11, 1, 1, 1, UnitUtils.Units.MM);
        assertEquals("G21G0X10Y10Z10F11", result);

        result = GcodeUtils.generateMoveCommand("G0", 10, 11, 1, 1, 1, UnitUtils.Units.MM);
        assertEquals("G21G0X10Y10Z10F11", result);

        result = GcodeUtils.generateMoveCommand("G91G0", 10, 11, 1, 0, 0, UnitUtils.Units.INCH);
        assertEquals("G20G91G0X10F11", result);

        result = GcodeUtils.generateMoveCommand("G91G0", 10, 11, 0, -1, -1, UnitUtils.Units.UNKNOWN);
        assertEquals("G91G0Y-10Z-10F11", result);

        result = GcodeUtils.generateMoveCommand("G1", 1.1, 11.1, 1, 0, -1, UnitUtils.Units.MM);
        assertEquals("G21G1X1.1Z-1.1F11.1", result);
    }