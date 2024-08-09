@Test
    public void testPurge_Predicate_RunTable ()
    {
        System.out.println("\n+++ purge_predicate_removed");

        Predicate<Run> predicate1 = new Predicate<Run>()
        {
            @Override
            public boolean check (Run run)
            {
                return run.getLength() == 2;
            }
        };

        RunTable instance = createHorizontalInstance();
        System.out.println("table before1:" + instance.dumpOf());
        instance.dumpSequences();

        RunTable removed = new RunTable("purged", HORIZONTAL, dim.width, dim.height);
        instance.purge(predicate1, removed);
        System.out.println("table after1:" + instance.dumpOf());
        instance.dumpSequences();

        System.out.println("purge after1:" + removed.dumpOf());
        removed.dumpSequences();

        Predicate<Run> predicate2 = new Predicate<Run>()
        {
            @Override
            public boolean check (Run run)
            {
                return run.getLength() == 1;
            }
        };

        instance.purge(predicate2, removed);
        System.out.println("table after2:" + instance.dumpOf());
        instance.dumpSequences();

        System.out.println("purge after2:" + removed.dumpOf());
        removed.dumpSequences();
    }