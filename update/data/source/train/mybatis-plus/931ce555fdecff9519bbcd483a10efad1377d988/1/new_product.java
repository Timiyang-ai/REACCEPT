public Wrapper<T> last(String limit) {
        sql.LAST(limit);
        return this;
    }