public static Data toHeapData(Data key) {
        if (key instanceof HeapData) {
            return key;
        }
        return new HeapData(key.toByteArray());
    }