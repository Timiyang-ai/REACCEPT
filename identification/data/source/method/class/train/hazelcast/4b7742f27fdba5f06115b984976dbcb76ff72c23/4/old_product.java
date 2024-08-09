public Data read(long sequence) {
        checkReadSequence(sequence);
        Object item = readOrLoadItem(sequence);
        return serializationService.toData(item);
    }