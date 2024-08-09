public RealmQuery<E> isNotEmpty(String fieldName) {
        realm.checkIfValid();

        FieldDescriptor fd = schema.getColumnIndices(fieldName, RealmFieldType.STRING, RealmFieldType.BINARY, RealmFieldType.LIST, RealmFieldType.LINKING_OBJECTS);
        this.query.isNotEmpty(fd.getColumnIndices(), fd.getNativeTablePointers());

        return this;
    }