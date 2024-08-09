public void include (RunTable that)
    {
        if (that == null) {
            throw new IllegalArgumentException("Cannot include a null runsTable");
        }

        if (that.orientation != orientation) {
            throw new IllegalArgumentException(
                    "Cannot include a runsTable of different orientation");
        }

        if (that.width != width) {
            throw new IllegalArgumentException("Cannot include a runsTable of different width");
        }

        if (that.height != height) {
            throw new IllegalArgumentException("Cannot include a runsTable of different height");
        }

        for (int row = 0; row < getSize(); row++) {
            RunSequence thisSeq = this.getSequence(row);
            RunSequence thatSeq = that.getSequence(row);

            for (Run thatRun : thatSeq) {
                thisSeq.add(thatRun);
            }
        }
    }