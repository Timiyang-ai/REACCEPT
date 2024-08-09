@Test
    public void testInclude ()
    {
        System.out.println("\n+++ include");

        RunTable instance = createHorizontalInstance();
        System.out.println("initial:\n" + instance.dumpOf());

        ByteProcessor buffer = instance.getBuffer();
        buffer.invert();

        GlobalFilter filter = new GlobalFilter(buffer, 127);
        RunTableFactory factory = new RunTableFactory(HORIZONTAL);
        RunTable that = factory.createTable(filter.filteredImage());
        System.out.println("inverted:\n" + that.dumpOf());
        that.dumpSequences();

        instance.include(that);
        System.out.println("full:\n" + instance.dumpOf());
        instance.dumpSequences();
        assertEquals(5, instance.getTotalRunCount());
    }