synchronized List<Write> close() {
        List<Write> items = new ArrayList<>(this.writes);
        this.writes.clear();
        this.totalLength = 0;
        this.closed = true;
        return items;
    }