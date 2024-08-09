public void include (RunTable that)
    {
        if (that == null) {
            throw new IllegalArgumentException("Cannot include a null RunTable");
        }

        if (that.orientation != orientation) {
            throw new IllegalArgumentException(
                    "Cannot include a RunTable of different orientation");
        }

        if (that.width != width) {
            throw new IllegalArgumentException("Cannot include a RunTable of different width");
        }

        if (that.height != height) {
            throw new IllegalArgumentException("Cannot include a RunTable of different height");
        }

        for (int row = 0, size = getSize(); row < size; row++) {
            for (Itr it = that.new Itr(row); it.hasNext();) {
                Run thatRun = it.next();
                addRun(row, thatRun);
            }
        }
    }