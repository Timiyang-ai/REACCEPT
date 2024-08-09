@SuppressWarnings({ "unchecked", "rawtypes" })
    public <TT extends T> StreamEx<TT> select(Class<TT> clazz) {
        return new StreamEx<>((Stream) stream.filter(clazz::isInstance));
    }