    private Object query(String pointer) {
        return new JSONPointer(pointer).queryFrom(document);
    }