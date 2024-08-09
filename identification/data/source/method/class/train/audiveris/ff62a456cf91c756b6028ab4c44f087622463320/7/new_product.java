public final Run getRunAt (int x,
                               int y)
    {
        Point oPt = orientation.oriented(new Point(x, y));

        // Protection
        if ((oPt.y < 0) || (oPt.y >= sequences.length)) {
            return null;
        }

        for (Itr it = new Itr(oPt.y); it.hasNext();) {
            Run run = it.next();

            if (run.getStart() > oPt.x) {
                return null;
            }

            if (run.getStop() >= oPt.x) {
                return run;
            }
        }

        return null;
    }