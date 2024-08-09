@Override
    public T get() {
        // using a local var speeds up the double-check idiom by 25%, see Effective Java, Item 71
        Supplier<? extends T> tmp = supplier;
        if (tmp != null) {
            synchronized (this) {
                tmp = supplier;
                if (tmp != null) {
                    value = tmp.get();
                    supplier = null; // free mem
                }
            }
        }
        return value;
    }