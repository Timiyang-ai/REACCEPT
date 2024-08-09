    @Test
    public void generateMoveToCommand() {
        String result = GcodeUtils.generateMoveToCommand("G90", new PartialPosition(1.0, 2.0, 3.1, UnitUtils.Units.MM), 1000.1);
        assertEquals("G21G90X1Y2Z3.1F1000.1", result);

        result = GcodeUtils.generateMoveToCommand("G90", new PartialPosition(-1.0, -2.0, -3.1, UnitUtils.Units.MM), 1000.1);
        assertEquals("G21G90X-1Y-2Z-3.1F1000.1", result);

        result = GcodeUtils.generateMoveToCommand("G90", new PartialPosition(-1.0, -2.0, -3.1, UnitUtils.Units.INCH), 10000);
        assertEquals("G20G90X-1Y-2Z-3.1F10000", result);

        result = GcodeUtils.generateMoveToCommand("G90", new PartialPosition(-1.0, -2.0, -3.1, UnitUtils.Units.INCH), 0);
        assertEquals("G20G90X-1Y-2Z-3.1", result);

        result = GcodeUtils.generateMoveToCommand("G90", new PartialPosition(-1.0, -2.0, -3.1, UnitUtils.Units.INCH), -10);
        assertEquals("G20G90X-1Y-2Z-3.1", result);

        result = GcodeUtils.generateMoveToCommand("G90G1", new PartialPosition(-1.0, -2.0, -3.1, UnitUtils.Units.INCH), -10);
        assertEquals("G20G90G1X-1Y-2Z-3.1", result);
    }