public List<FutureReadResultEntry> close() {
        List<FutureReadResultEntry> result;
        synchronized (this.reads) {
            if (this.closed) {
                result = Collections.emptyList();
            } else {
                result = new ArrayList<>(this.reads);
                this.reads.clear();
                this.closed = true;
            }
        }

        return result;
    }