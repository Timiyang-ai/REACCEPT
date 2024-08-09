void add(final Data d) {
    if(size == data.length) {
      data = Arrays.copyOf(data, size << 1);
      pins = Arrays.copyOf(pins, size << 1);
    }
    data[size] = d;
    pins[size++] = 1;
  }