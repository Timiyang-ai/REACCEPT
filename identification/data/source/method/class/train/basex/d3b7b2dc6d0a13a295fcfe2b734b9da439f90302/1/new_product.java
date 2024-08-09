public final boolean containsAll(final StringList l) {
    if(isEmpty() && !l.isEmpty()) return false;
    int i = 0;
    for(final String e : l) {
      int result;
      while(0 != (result = list[i].compareTo(e))) {
        if(++i >= size() || result > 0) return false;
      }
    }
    return true;
  }