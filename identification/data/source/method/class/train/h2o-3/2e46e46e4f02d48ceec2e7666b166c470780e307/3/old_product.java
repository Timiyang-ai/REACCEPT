public void moveFirst( int cols[] ) {
    boolean colsMoved[] = new boolean[_keys.length];
    Vec tmpvecs[] = vecs().clone();
    Key tmpkeys[] = _keys.clone();
    String tmpnames[] = _names.clone();

    // Move the desired ones first
    for (int i=0; i<cols.length; i++) {
      int w = cols[i];
      if (colsMoved[w]) throw new IllegalArgumentException("Duplicates in column numbers passed in");
      if (w<0 || w>=_keys.length) throw new IllegalArgumentException("column number out of 0-based range");
      colsMoved[w] = true;
      tmpvecs[i] = _vecs[w];
      tmpkeys[i] = _keys[w];
      tmpnames[i] = _names[w];
    }

    // Put the other ones afterwards
    int w = cols.length;
    for (int i=0; i<_keys.length; i++) {
      if (!colsMoved[i]) {
        tmpvecs[w] = _vecs[i];
        tmpkeys[w] = _keys[i];
        tmpnames[w] = _names[i];
        w++;
      }
    }

    // Copy back over the original in-place
    for (int i=0; i<_keys.length; i++) {
      _vecs[i] = tmpvecs[i];
      _keys[i] = tmpkeys[i];
      _names[i] = tmpnames[i];
    }
  }