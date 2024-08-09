@Test
    public void testAddCommand_String_int() throws Exception {
        System.out.println("addCommand");
        GcodeParser instance = new GcodeParser();

        // More or less the same thing as the above test, so just make sure the
        // line number is applied.
        List<GcodeMeta> results = instance.addCommand("G20 G0X1F150", 123);
        testCommand(results, 1, 150, 1., 0., 0., true, false, false, false, false, 123);
    }