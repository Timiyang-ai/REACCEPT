public int getTotalRunCount ()
    {
        int total = 0;

        for (RunSequence seq : sequences) {
            total += seq.size();
        }

        return total;
    }