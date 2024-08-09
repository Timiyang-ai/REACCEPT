@Override
    public boolean equals(Object obj) {
        return internalEquals(obj) || internalEqualsNext(obj);
    }