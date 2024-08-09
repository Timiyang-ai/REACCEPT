public static Data toHeapData(Data data) {
        if (data == null || data instanceof HeapData) {
            return data;
        }
        return new HeapData(data.toByteArray());
    }