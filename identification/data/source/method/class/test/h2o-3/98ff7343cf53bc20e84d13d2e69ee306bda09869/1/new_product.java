@Override public void addNumCol(int colIdx, double value) {
    if (Double.isNaN(value)) {
      addInvalidCol(colIdx);
    } else {
      double d= value;
      int exp = 0;
      long number = (long)d;
      while (number != d) {
        d *= 10;
        --exp;
        number = (long)d;
      }
      addNumCol(colIdx, number, exp);
    }
  }