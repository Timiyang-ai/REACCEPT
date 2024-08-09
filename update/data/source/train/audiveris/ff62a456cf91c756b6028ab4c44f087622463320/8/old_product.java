public RunTable copy (String name)
    {
        RunTable clone = new RunTable(name, orientation, width, height);

        for (int i = 0; i < sequences.length; i++) {
            BasicRunSequence seq = (BasicRunSequence) getSequence(i);
            clone.sequences[i] = new BasicRunSequence(seq);
        }

        return clone;
    }