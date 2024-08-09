public void moveFirst( int cols[] ) {
    String [] names = new String[_names.length];
    int j = 0, k = cols.length;
    for(int i = 0; i < _names.length; ++i){
      if(i == cols[j]){
        names[j++] = _names[i];
      } else
        names[k++] = _names[i];
    }
    _names = names;
    _vecs.moveFirst(cols);
    _sorted_ids = null;
  }