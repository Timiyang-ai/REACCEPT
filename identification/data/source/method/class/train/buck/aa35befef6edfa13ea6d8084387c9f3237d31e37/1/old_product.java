default RichStream<T> concat(Stream<T> other) {
    return new RichStreamImpl<>(Stream.concat(this, other));
  }