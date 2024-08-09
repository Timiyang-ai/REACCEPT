public void removeRun (int pos,
                           Run run)
    {
        RunSequence seq = getSequence(pos);

        if (!seq.remove(run)) {
            throw new RuntimeException(this + " Cannot find " + run + " at pos " + pos);
        }
    }