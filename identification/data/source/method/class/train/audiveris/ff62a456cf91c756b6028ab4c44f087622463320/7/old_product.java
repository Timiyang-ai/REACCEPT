public final Run getRunAt (int x,
                               int y)
    {
        Point oPt = orientation.oriented(new Point(x, y));

        // Protection
        if ((oPt.y < 0) || (oPt.y >= sequences.length)) {
            return null;
        }

        RunSequence seq = getSequence(oPt.y);

        for (Run run : seq) {
            if (run.getStart() > oPt.x) {
                return null;
            }

            if (run.getStop() >= oPt.x) {
                return run;
            }
        }

        return null;
    }