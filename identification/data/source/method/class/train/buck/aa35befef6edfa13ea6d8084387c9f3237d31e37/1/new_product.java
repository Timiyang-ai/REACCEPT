default RichStream<T> concat(Stream<? extends T> other) {
    return new RichStreamImpl<>(Stream.concat(this, other));
  }