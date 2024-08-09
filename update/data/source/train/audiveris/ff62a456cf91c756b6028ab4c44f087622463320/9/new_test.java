@Test
    public void testDumpSequences ()
    {
        System.out.println("\n+++ dumpSequences");

        RunTable instance = createHorizontalInstance();
        instance.dumpSequences();

        instance = createVerticalInstance();
        instance.dumpSequences();
    }