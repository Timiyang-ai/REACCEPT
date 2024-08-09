@SuppressWarnings("unchecked")
    public <TT> StreamEx<TT> select(Class<TT> clazz) {
        return (StreamEx<TT>) filter(clazz::isInstance);
    }