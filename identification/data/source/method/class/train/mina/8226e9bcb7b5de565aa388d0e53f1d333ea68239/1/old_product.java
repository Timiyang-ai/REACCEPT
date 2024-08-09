public IoBuffer clear() {
        position = 0;
        mark = UNSET_MARK;

        return this;
    }