private Container not(BitmapContainer answer, final int firstOfRange,
                          final int lastOfRange) {
        assert bitmap.length == MAX_CAPACITY / 64; // checking assumption
        // that partial
        // bitmaps are not
        // allowed
        // an easy case for full range, should be common
        if (lastOfRange - firstOfRange + 1 == MAX_CAPACITY) {
            final int newCardinality = MAX_CAPACITY - cardinality;
            for (int k = 0; k < this.bitmap.length; ++k)
                answer.bitmap[k] = ~this.bitmap[k];
            answer.cardinality = newCardinality;
            if (newCardinality <= ArrayContainer.DEFAULT_MAX_SIZE)
                return answer.toArrayContainer();
            return answer;
        }

        // could be optimized to first determine the answer cardinality,
        // rather than update/create bitmap and then possibly convert

        int cardinalityChange = 0;
        final int rangeFirstWord = firstOfRange / 64;
        final int rangeFirstBitPos = firstOfRange & 63;
        final int rangeLastWord = lastOfRange / 64;
        final long rangeLastBitPos = lastOfRange & 63;

        // if not in place, we need to duplicate stuff before
        // rangeFirstWord and after rangeLastWord
        if (answer != this) {
            System.arraycopy(bitmap, 0, answer.bitmap, 0, rangeFirstWord);
            System.arraycopy(bitmap, rangeLastWord + 1, answer.bitmap,
                    rangeLastWord + 1, bitmap.length - (rangeLastWord + 1));
        }

        // unfortunately, the simple expression gives the wrong mask for
        // rangeLastBitPos==63
        // no branchless way comes to mind
        final long maskOnLeft = (rangeLastBitPos == 63) ? -1L : (1L << (rangeLastBitPos + 1)) - 1;

        long mask = -1L; // now zero out stuff in the prefix
        mask ^= ((1L << rangeFirstBitPos) - 1);

        if (rangeFirstWord == rangeLastWord) {
            // range starts and ends in same word (may have
            // unchanged bits on both left and right)
            mask &= maskOnLeft;
            cardinalityChange = -Long.bitCount(bitmap[rangeFirstWord]);
            answer.bitmap[rangeFirstWord] = bitmap[rangeFirstWord] ^ mask;
            cardinalityChange += Long.bitCount(answer.bitmap[rangeFirstWord]);
            answer.cardinality = cardinality + cardinalityChange;

            if (answer.cardinality <= ArrayContainer.DEFAULT_MAX_SIZE)
                return answer.toArrayContainer();
            return answer;
        }

        // range spans words
        cardinalityChange += -Long.bitCount(bitmap[rangeFirstWord]);
        answer.bitmap[rangeFirstWord] = bitmap[rangeFirstWord] ^ mask;
        cardinalityChange += Long.bitCount(answer.bitmap[rangeFirstWord]);

        cardinalityChange += -Long.bitCount(bitmap[rangeLastWord]);
        answer.bitmap[rangeLastWord] = bitmap[rangeLastWord] ^ maskOnLeft;
        cardinalityChange += Long.bitCount(answer.bitmap[rangeLastWord]);

        // negate the words, if any, strictly between first and last
        for (int i = rangeFirstWord + 1; i < rangeLastWord; ++i) {
            cardinalityChange += (64 - 2 * Long.bitCount(bitmap[i]));
            answer.bitmap[i] = ~bitmap[i];
        }
        answer.cardinality = cardinality + cardinalityChange;

        if (answer.cardinality <= ArrayContainer.DEFAULT_MAX_SIZE)
            return answer.toArrayContainer();
        return answer;
    }