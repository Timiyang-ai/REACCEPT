public void removeRun (int index,
                           Run run)
    {
        // Find where this run lies in rle
        Iterator<Run> iter = new Itr(index);

        while (iter.hasNext()) {
            Run r = iter.next();

            if (r.isIdentical(run)) {
                // We are located on the right run
                iter.remove();

                return;
            }
        }

        throw new RuntimeException(this + " Cannot find " + run + " at pos " + index);
    }