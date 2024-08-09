public synchronized long addToTableSize(String table_name, long size) {
        Long orig_size = this.table_sizes.get(table_name);
        if (orig_size == null) {
            orig_size = 0l;
        }
        long new_size = orig_size + size;
        this.setTableSize(table_name, new_size);
        return (new_size);
    }