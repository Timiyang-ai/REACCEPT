@NotNull
    public OffsetInfo getOffsetInfo(int offset, boolean isEndOffset) {
        // if is end offset then will not
        int offsetEnd = isEndOffset ? offset : offset + 1;

        // if offsetEnd <= firstSegment.startOffset then indexRange is [0,0)
        // if offset >= lastSegment.endOffset then indexRange is [sequence.length, sequence.length)

        // otherwise, find segment for the offset in the segmentOffsetTree:

        // if offsetEnd > segment.startOffset && offset < segment.endOffset then
        //      indexRange.start = segment.startIndex + offset - segment.startOffset, indexRange.length = offsetEnd-offset

        // if offsetEnd == segment.startOffset
        //      indexRange is preceding TEXT segment indexRange or if none then [segment.startIndex, segment.startIndex)

        // if offset == segment.endOffset
        //      indexRange is preceding TEXT segment indexRange or if none then [segment.startIndex, segment.startIndex)

        OffsetInfo lastResult;

        if (offsetEnd <= sequence.getStartOffset()) {
            // before sequence
            lastResult = new OffsetInfo(offset, true, 0);
        } else if (offset >= sequence.getEndOffset()) {
            // after sequence
            lastResult = new OffsetInfo(offset, true, sequence.length());
        } else {
            Segment seg = segmentOffsetTree.findSegmentByOffset(offset, sequence.getBaseSequence(), lastSegment);
            assert seg != null;
            lastSegment = seg;

            if (offsetEnd > seg.getStartOffset() && offset < seg.getEndOffset()) {
                // inside base segment
                int startIndex = seg.getStartIndex() + offset - seg.getStartOffset();
                int endIndex = seg.getStartIndex() + offsetEnd - seg.getStartOffset();
                lastResult = new OffsetInfo(offset, isEndOffset, startIndex, endIndex);
            } else if (offsetEnd <= seg.getStartOffset()) {
                int startIndex;
                int endIndex;
                Segment textSegment = segmentOffsetTree.getPreviousText(seg, sequence);
                if (textSegment != null) {
                    startIndex = textSegment.getStartIndex();
                    endIndex = textSegment.getEndIndex();
                } else {
                    endIndex = startIndex = seg.getStartIndex();
                }
                lastResult = new OffsetInfo(offset, true, startIndex, endIndex);
            } else if (offset >= seg.getEndOffset()) {
                int startIndex;
                int endIndex;
                Segment textSegment = segmentOffsetTree.getNextText(seg, sequence);
                if (textSegment != null) {
                    startIndex = textSegment.getStartIndex();
                    endIndex = textSegment.getEndIndex();
                } else {
                    endIndex = startIndex = seg.getEndIndex();
                }
                lastResult = new OffsetInfo(offset, true, startIndex, endIndex);
            } else {
                throw new IllegalStateException(String.format("Unexpected offset: [%d, %d), seg: %s, not inside nor at start nor at end", offset, offsetEnd, seg.toString()));
            }
        }

        return lastResult;
    }