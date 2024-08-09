public RunTable copy (String name)
    {
        RunTable clone = new RunTable(name, orientation, width, height);

        for (int i = 0; i < sequences.length; i++) {
            short[] seq = getSequence(i);

            if (seq != null) {
                short[] rle = new short[seq.length];
                System.arraycopy(seq, 0, rle, 0, seq.length);
                clone.sequences[i] = rle;
            }
        }

        return clone;
    }