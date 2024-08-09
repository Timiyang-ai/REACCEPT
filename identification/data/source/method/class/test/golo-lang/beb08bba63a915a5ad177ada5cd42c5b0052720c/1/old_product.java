public LazyList tail() {
    if (memoTail == null) {
      try {
        memoTail = (LazyList) (this.tail.invokeWithArguments());
      } catch (Throwable e) {
        memoTail = EMPTY;
      }
    }
    return memoTail;
  }