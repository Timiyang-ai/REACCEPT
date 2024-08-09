public int[] sort(final Comparator<BytesRef> comp) {
    final int[] compact = compact();
    new SorterTemplate() {
      @Override
      protected void swap(int i, int j) {
        final int o = compact[i];
        compact[i] = compact[j];
        compact[j] = o;
      }
      
      @Override
      protected int compare(int i, int j) {
        final int id1 = compact[i], id2 = compact[j];
        assert bytesStart.length > id1 && bytesStart.length > id2;
        pool.setBytesRef(scratch1, bytesStart[id1]);
        pool.setBytesRef(scratch2, bytesStart[id2]);
        return comp.compare(scratch1, scratch2);
      }

      @Override
      protected void setPivot(int i) {
        final int id = compact[i];
        assert bytesStart.length > id;
        pool.setBytesRef(pivot, bytesStart[id]);
      }
  
      @Override
      protected int comparePivot(int j) {
        final int id = compact[j];
        assert bytesStart.length > id;
        pool.setBytesRef(scratch2, bytesStart[id]);
        return comp.compare(pivot, scratch2);
      }
      
      private final BytesRef pivot = new BytesRef(),
        scratch1 = new BytesRef(), scratch2 = new BytesRef();
    }.quickSort(0, count - 1);
    return compact;
  }