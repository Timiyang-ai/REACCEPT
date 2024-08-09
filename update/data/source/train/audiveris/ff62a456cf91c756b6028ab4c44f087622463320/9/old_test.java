@Test
    public void testDumpSequences ()
    {
        System.out.println("dumpSequences");

        RunTable instance = createHorizontalInstance();
        instance.dumpSequences();

        instance = createVerticalInstance();
        instance.dumpSequences();
    }