public boolean isIdentical (RunTable that)
    {
        // Check null entities
        if (that == null) {
            return false;
        }

        if ((this.orientation == that.orientation)
            && (this.width == that.width)
            && (this.height == that.height)) {
            // Check run sequences, row by row
            for (int row = 0; row < getSize(); row++) {
                RunSequence thisSeq = getSequence(row);
                RunSequence thatSeq = that.getSequence(row);

                if (!thisSeq.equals(thatSeq)) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }