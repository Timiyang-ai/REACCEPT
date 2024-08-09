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
        final int ord1 = compact[i], ord2 = compact[j];
        assert bytesStart.length > ord1 && bytesStart.length > ord2;
        pool.setBytesRef(scratch1, bytesStart[ord1]);
        pool.setBytesRef(scratch2, bytesStart[ord2]);
        return comp.compare(scratch1, scratch2);
      }

      @Override
      protected void setPivot(int i) {
        final int ord = compact[i];
        assert bytesStart.length > ord;
        pool.setBytesRef(pivot, bytesStart[ord]);
      }
  
      @Override
      protected int comparePivot(int j) {
        final int ord = compact[j];
        assert bytesStart.length > ord;
        pool.setBytesRef(scratch2, bytesStart[ord]);
        return comp.compare(pivot, scratch2);
      }
      
      private final BytesRef pivot = new BytesRef(),
        scratch1 = new BytesRef(), scratch2 = new BytesRef();
    }.quickSort(0, count - 1);
    return compact;
  }