public synchronized void addToTableSize(String table_name, long size) {
        this.table_sizes.put(table_name, size);
    }