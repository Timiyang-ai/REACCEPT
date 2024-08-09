public int getTotalRunCount ()
    {
        int total = 0;

        for (short[] seq : sequences) {
            total += sequenceSize(seq);
        }

        return total;
    }