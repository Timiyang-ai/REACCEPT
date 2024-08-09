@Override
    public T get() {
        if (isEmpty()) {
            synchronized (this) {
                if (isEmpty()) {
                    value = supplier.get();
                    supplier = null; // free mem
                }
            }
        }
        return value;
    }