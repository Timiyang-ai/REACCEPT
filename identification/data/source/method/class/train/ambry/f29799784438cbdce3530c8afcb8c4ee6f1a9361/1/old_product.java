@Deprecated
  StoreMessageReadSet getView(List<BlobReadOptions> readOptions) {
    LogSegment firstLogSegment = segmentsByName.firstEntry().getValue();
    Pair<File, FileChannel> view = firstLogSegment.getView();
    return new StoreMessageReadSet(view.getFirst(), view.getSecond(), readOptions, firstLogSegment.getEndOffset());
  }