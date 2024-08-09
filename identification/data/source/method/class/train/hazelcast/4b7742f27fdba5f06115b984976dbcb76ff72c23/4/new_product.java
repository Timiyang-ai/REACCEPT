public Data readAsData(long sequence) {
        checkReadSequence(sequence);
        Object rbItem = readOrLoadItem(sequence);
        return serializationService.toData(rbItem);
    }