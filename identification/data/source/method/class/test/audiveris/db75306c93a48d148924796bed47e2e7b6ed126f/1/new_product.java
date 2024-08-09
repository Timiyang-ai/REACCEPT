public RunTable copy ()
    {
        RunTable clone = new RunTable(orientation, width, height);

        for (int i = 0; i < sequences.length; i++) {
            RunSequence seq = sequences[i];

            if (seq != null) {
                short[] rle = new short[seq.rle.length];
                System.arraycopy(seq.rle, 0, rle, 0, seq.rle.length);
                clone.sequences[i] = new RunSequence(rle);
            }
        }

        return clone;
    }