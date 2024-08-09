public LazyList tail() {
    if (memoTail == null) {
      try {
        memoTail = (LazyList) (this.tail.invoke());
      } catch (Throwable e) {
        memoTail = EMPTY;
      }
    }
    return memoTail;
  }