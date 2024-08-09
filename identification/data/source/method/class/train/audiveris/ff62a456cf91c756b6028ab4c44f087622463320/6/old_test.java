@Test
    public void testPurge_Predicate_RunTable ()
    {
        System.out.println("purge_predicate_removed");

        Predicate<Run> predicate1 = new Predicate<Run>()
        {
            @Override
            public boolean check (Run run)
            {
                return run.getLength() == 2;
            }
        };

        RunTable instance = createHorizontalInstance();
        instance.dumpSequences();
        System.out.println("table before1:" + instance.dumpOf());

        RunTable removed = new RunTable("purged", HORIZONTAL, dim.width, dim.height);
        instance.purge(predicate1, removed);
        instance.dumpSequences();
        System.out.println("table after1:" + instance.dumpOf());

        removed.dumpSequences();
        System.out.println("purge after1:" + removed.dumpOf());

        Predicate<Run> predicate2 = new Predicate<Run>()
        {
            @Override
            public boolean check (Run run)
            {
                return run.getLength() == 1;
            }
        };

        instance.purge(predicate2, removed);
        instance.dumpSequences();
        System.out.println("table after2:" + instance.dumpOf());

        removed.dumpSequences();
        System.out.println("purge after2:" + removed.dumpOf());
    }