boolean isIdentical (RunTable that)
    {
        // Check null entities
        if (that == null) {
            return false;
        }

        if ((this.orientation == that.orientation)
            && (this.width == that.width)
            && (this.height == that.height)) {
            // Check run sequences, row by row
            for (int row = 0, size = getSize(); row < size; row++) {
                short[] thisSeq = getSequence(row);
                short[] thatSeq = that.getSequence(row);

                if (!Arrays.equals(thisSeq, thatSeq)) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }