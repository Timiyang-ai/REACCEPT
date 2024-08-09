public Wrapper<T> limit(int begin, int end) {
        sql.LIMIT(begin, end);
        return this;
    }