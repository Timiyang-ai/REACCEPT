public Run lookupRun (Point point)
    {
        Point oPt = orientation.oriented(point);

        if ((oPt.y < 0) || (oPt.y >= getSize())) {
            return null;
        }

        for (Run run : getSequence(oPt.y)) {
            if (run.getStart() > oPt.x) {
                return null;
            }

            if (run.getStop() >= oPt.x) {
                return run;
            }
        }

        return null;
    }