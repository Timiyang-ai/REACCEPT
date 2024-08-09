public int indexOf(Object o) {
    int idx = 0;
    for (Object elt : this) {
      if (elt.equals(o)) return idx;
      idx++;
    }
    return -1;
  }