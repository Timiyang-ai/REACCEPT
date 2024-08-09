public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }