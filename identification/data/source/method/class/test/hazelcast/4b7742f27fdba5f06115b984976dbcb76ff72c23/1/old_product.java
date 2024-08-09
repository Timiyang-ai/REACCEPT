public Data read(long sequence) {
        checkReadSequence(sequence);

        int index = toIndex(sequence);
        Object item = ringItems[index];
        return serializationService.toData(item);
    }