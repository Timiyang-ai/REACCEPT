public Run lookupRun (Point point)
    {
        Point oPt = orientation.oriented(point);

        if ((oPt.y < 0) || (oPt.y >= getSize())) {
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