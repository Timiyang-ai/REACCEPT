@Test
    public void testInclude ()
    {
        System.out.println("\n+++ include");

        RunTable instance = createHorizontalInstance();

        ByteProcessor buffer = instance.getBuffer();
        buffer.invert();

        GlobalFilter filter = new GlobalFilter(buffer, 127);
        RunTableFactory factory = new RunTableFactory(HORIZONTAL);
        RunTable that = factory.createTable("inverted", filter.filteredImage());
        System.out.println("inverted" + that.dumpOf());
        that.dumpSequences();

        instance.include(that);
        System.out.println("full" + instance.dumpOf());
        instance.dumpSequences();
        assertEquals(5, instance.getTotalRunCount());
    }