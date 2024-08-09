@Test
    public void testRemoveRun ()
    {
        System.out.println("\n+++ removeRun");

        int pos = 3;
        Run run = new Run(4, 1);
        RunTable instance = createHorizontalInstance();
        short[] seq1 = instance.getSequence(pos);
        System.out.println("sequence before: " + Arrays.toString(seq1));
        System.out.println("table before:" + instance.dumpOf());
        instance.removeRun(pos, run);

        short[] seq2 = instance.getSequence(pos);
        System.out.println("sequence  after: " + Arrays.toString(seq2));
        System.out.println("table after:" + instance.dumpOf());
    }