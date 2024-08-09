public void add(final Nod n) {
    if(size == item.length) {
      final Nod[] tmp = new Nod[Array.newSize(size)];
      System.arraycopy(item, 0, tmp, 0, size);
      item = tmp;
    }
    if(dupl && !sort) sort = size != 0 && item[size - 1].diff(n) > 0;
    item[size++] = n;
  }