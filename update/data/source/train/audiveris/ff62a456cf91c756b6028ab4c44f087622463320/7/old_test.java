@Test
    public void testInclude ()
    {
        System.out.println("include");

        RunTable instance = createHorizontalInstance();

        ByteProcessor buffer = instance.getBuffer();
        buffer.invert();

        GlobalFilter filter = new GlobalFilter(buffer, 127);
        RunTableFactory factory = new RunTableFactory(HORIZONTAL);
        RunTable that = factory.createTable("inverted", filter.filteredImage());
        that.dumpSequences();
        System.out.println("inverted" + that.dumpOf());

        instance.include(that);
        instance.dumpSequences();
        System.out.println("full" + instance.dumpOf());
        assertEquals(5, instance.getTotalRunCount());
    }