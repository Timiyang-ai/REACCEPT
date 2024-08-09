public int getOffsetIndex(int offset, @NotNull PositionAnchor anchor) {
        Segment seg = segmentOffsetTree.findSegmentByOffset(offset, sequence.getBaseSequence(), lastSegment);
        if (seg == null) {
            assert offset < sequence.getStartOffset() || offset > sequence.getEndOffset();
            return offset < sequence.getStartOffset() ? 0 : sequence.length();
        }

        lastSegment = seg;

        if (seg.offsetNotInSegment(offset)) {
            // offset in missing segment, need to check the context of the offset in original sequence
            // and compare to result sequence index
            if (offset < seg.getStartOffset()) {
                if (seg.getPos() > 0) {
                    Segment prevSeg = segmentOffsetTree.getSegment(seg.getPos() - 1, sequence.getBaseSequence());
                    int indexInPrev = offset == prevSeg.getEndOffset() ? prevSeg.length() : prevSeg.length() - (seg.getStartOffset() - offset);
                    assert indexInPrev >= 0 && indexInPrev <= prevSeg.length();

                    switch (anchor) {
                        case CURRENT:
                        case NEXT:
                            return seg.getStartIndex();

                        default:
                        case PREVIOUS:
                            return prevSeg.getStartIndex() + indexInPrev;
                    }
                }
            } else {
                if (offset == seg.getEndOffset()) {
                    return seg.getStartIndex() + seg.length();
                } else if (seg.getPos() + 1 < segmentOffsetTree.size()) {
                    Segment nextSeg = segmentOffsetTree.getSegment(seg.getPos() + 1, sequence.getBaseSequence());
                    int indexInNext = offset - seg.getEndOffset();
                    assert indexInNext >= 0 && indexInNext <= nextSeg.length();

                    switch (anchor) {
                        case PREVIOUS:
                            return seg.getStartIndex();

                        default:
                        case NEXT:
                        case CURRENT:
                            return nextSeg.getStartIndex() + indexInNext;
                    }
                }
            }
        }

        return offset - seg.getStartOffset() + seg.getStartIndex();
    }