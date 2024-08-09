synchronized List<Write> clear() {
        List<Write> items = new ArrayList<>(this.writes);
        this.writes.clear();
        this.totalLength = 0;
        return items;
    }