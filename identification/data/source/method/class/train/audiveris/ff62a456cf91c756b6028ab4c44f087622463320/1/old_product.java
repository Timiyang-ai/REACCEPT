public void dumpSequences ()
    {
        System.out.println(toString());

        for (int i = 0; i < sequences.length; i++) {
            RunSequence seq = sequences[i];
            System.out.printf("%4d:%s%n", i, seq.toString());
        }
    }