public final Buffer clear() {
        position = 0;
        mark = UNSET_MARK;
        limit = capacity;
        return this;
    }