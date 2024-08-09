static <T> void crossover(final MSeq<T> that, final MSeq<T> other, final int index) {
    assert (index >= 0) : format(
      "Crossover index must be within [0, %d) but was %d",
      that.length(), index
    );

    that.swap(index, index+1, other, index);
  }