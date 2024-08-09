@SuppressWarnings("unchecked")
    public <TT extends T> StreamEx<TT> select(Class<TT> clazz) {
        return (StreamEx<TT>) filter(clazz::isInstance);
    }