public LazyList tail() {
    try {
      return (LazyList) (this.tail.invokeWithArguments());
    } catch (Throwable e) {
      return EMPTY;
    }
  }