FileSpan getFileSpanForMessage(Offset endOffsetOfPrevMessage, long size) {
    LogSegment segment = segmentsByName.get(endOffsetOfPrevMessage.getName());
    long startOffset = endOffsetOfPrevMessage.getOffset();
    if (startOffset > segment.getEndOffset()) {
      throw new IllegalArgumentException("Start offset provided is greater than segment end offset");
    } else if (startOffset == segment.getEndOffset()) {
      // current segment has ended. Since a blob will be wholly contained within one segment, this blob is in the
      // next segment
      segment = getNextSegment(segment);
      startOffset = segment.getStartOffset();
    } else if (startOffset + size > segment.getEndOffset()) {
      throw new IllegalStateException("Args indicate that blob is not wholly contained within a single segment");
    }
    return new FileSpan(new Offset(segment.getName(), startOffset), new Offset(segment.getName(), startOffset + size));
  }