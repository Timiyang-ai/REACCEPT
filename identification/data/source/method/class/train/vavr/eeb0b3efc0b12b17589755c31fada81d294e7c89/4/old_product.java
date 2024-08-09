@Override
    public T get() {
        Supplier<? extends T> tmp = supplier;
        if (tmp != null) {
            synchronized (this) {
                tmp = supplier;
                if (tmp != null) {
                    value = supplier.get();
                    supplier = null; // free mem
                }
            }
        }
        return value;
    }