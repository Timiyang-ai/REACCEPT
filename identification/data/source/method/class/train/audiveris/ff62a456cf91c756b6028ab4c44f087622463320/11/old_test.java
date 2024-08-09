@Test
    public void testRemoveRun ()
    {
        System.out.println("removeRun");

        int pos = 3;
        Run run = new Run(4, 1);
        RunTable instance = createHorizontalInstance();
        RunSequence seq1 = instance.getSequence(pos);
        System.out.println("sequence before: " + seq1);
        System.out.println("table before:" + instance.dumpOf());
        instance.removeRun(pos, run);

        RunSequence seq2 = instance.getSequence(pos);
        System.out.println("sequence  after: " + seq2);
        System.out.println("table after:" + instance.dumpOf());
    }