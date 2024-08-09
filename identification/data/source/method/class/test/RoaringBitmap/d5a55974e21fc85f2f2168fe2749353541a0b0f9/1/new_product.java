private MappeableContainer not(MappeableBitmapContainer answer,
            final int firstOfRange, final int lastOfRange) {
        // TODO: this can be optimized for performance
        assert bitmap.limit() == MAX_CAPACITY / 64; // checking
        // assumption
        // that partial
        // bitmaps are not
        // allowed
        // an easy case for full range, should be common
        if (lastOfRange - firstOfRange  == MAX_CAPACITY) {
            final int newCardinality = MAX_CAPACITY - cardinality;
            for (int k = 0; k < this.bitmap.limit(); ++k)
                answer.bitmap.put(k, ~this.bitmap.get(k));
            answer.cardinality = newCardinality;
            if (newCardinality <= MappeableArrayContainer.DEFAULT_MAX_SIZE)
                return answer.toArrayContainer();
            return answer;
        }

        // could be optimized to first determine the answer cardinality,
        // rather than update/create bitmap and then possibly convert

        int cardinalityChange = 0;
        final int rangeFirstWord = firstOfRange / 64;
        final int rangeFirstBitPos = firstOfRange & 63;
        final int rangeLastWord = (lastOfRange - 1) / 64;
        final long rangeLastBitPos = (lastOfRange - 1) & 63;

        // if not in place, we need to duplicate stuff before
        // rangeFirstWord and after rangeLastWord
        if (answer != this) {
            for (int i = 0; i < rangeFirstWord; ++i)
                answer.bitmap.put(i, bitmap.get(i));
            for (int i = rangeLastWord + 1; i < bitmap.limit(); ++i)
                answer.bitmap.put(i, bitmap.get(i));
        }

        // unfortunately, the simple expression gives the wrong mask for
        // rangeLastBitPos==63
        // no branchless way comes to mind
        final long maskOnLeft = (rangeLastBitPos == 63) ? -1L
                : (1L << (rangeLastBitPos + 1)) - 1;

        long mask = -1L; // now zero out stuff in the prefix
        mask ^= ((1L << rangeFirstBitPos) - 1);

        if (rangeFirstWord == rangeLastWord) {
            // range starts and ends in same word (may have
            // unchanged bits on both left and right)
            mask &= maskOnLeft;
            cardinalityChange = -Long.bitCount(bitmap.get(rangeFirstWord));
            answer.bitmap
                    .put(rangeFirstWord, bitmap.get(rangeFirstWord) ^ mask);
            cardinalityChange += Long.bitCount(answer.bitmap
                    .get(rangeFirstWord));
            answer.cardinality = cardinality + cardinalityChange;

            if (answer.cardinality <= MappeableArrayContainer.DEFAULT_MAX_SIZE)
                return answer.toArrayContainer();
            return answer;
        }

        // range spans words
        cardinalityChange += -Long.bitCount(bitmap.get(rangeFirstWord));
        answer.bitmap.put(rangeFirstWord, bitmap.get(rangeFirstWord) ^ mask);
        cardinalityChange += Long.bitCount(answer.bitmap.get(rangeFirstWord));

        cardinalityChange += -Long.bitCount(bitmap.get(rangeLastWord));
        answer.bitmap
                .put(rangeLastWord, bitmap.get(rangeLastWord) ^ maskOnLeft);
        cardinalityChange += Long.bitCount(answer.bitmap.get(rangeLastWord));

        // negate the words, if any, strictly between first and last
        for (int i = rangeFirstWord + 1; i < rangeLastWord; ++i) {
            cardinalityChange += (64 - 2 * Long.bitCount(bitmap.get(i)));
            answer.bitmap.put(i, ~bitmap.get(i));
        }
        answer.cardinality = cardinality + cardinalityChange;

        if (answer.cardinality <= MappeableArrayContainer.DEFAULT_MAX_SIZE)
            return answer.toArrayContainer();
        return answer;
    }