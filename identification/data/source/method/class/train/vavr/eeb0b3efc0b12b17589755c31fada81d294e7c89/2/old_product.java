@Override
    public T get() {
        if (value.isEmpty()) {
            synchronized (this) {
                if (value.isEmpty()) {
                    value = new Some<>(supplier.get());
                    supplier = null; // free mem
                }
            }
        }
        return value.get();
    }