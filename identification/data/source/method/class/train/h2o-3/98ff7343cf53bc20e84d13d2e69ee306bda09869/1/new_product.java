@Override public void addNumCol(int colIdx, double value) {
    if (Double.isNaN(value) || Double.isInfinite(value)) {
      addInvalidCol(colIdx);
    } else {
      if( colIdx < _nCols ) {
        _nvs[_col = colIdx].addNumDecompose(value);
        if(_ctypes != null && _ctypes[colIdx] == Vec.T_BAD ) _ctypes[colIdx] = Vec.T_NUM;
      }
    }
  }