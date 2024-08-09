public void addFeature(TransactionTrace txn, String key, Object val, Type type) {
        String txn_id = txn.getTransactionId();
        
        // Add the attribute if it's new
        if (!this.attributes.containsKey(key)) {
            // Figure out what type it is
            if (type == null) {
                Class<?> valClass = val.getClass();
                if (valClass.equals(Boolean.class) || valClass.equals(boolean.class)) {
                    type = Type.BOOLEAN;
                } else if (ClassUtil.getSuperClasses(valClass).contains(Number.class)) {
                    type = Type.NUMERIC;
                } else if (val instanceof String) {
                    type = Type.STRING;
                } else {
                    type = Type.RANGE;
                }
            }
            LOG.debug("Adding new attribute " + key + " [" + type + "]");
            this.attributes.put(key, type);
        }
        
        // Store ranges if needed
        if (type == Type.RANGE || type == Type.BOOLEAN) {
            if (!this.attribute_ranges.containsKey(key)) {
                this.attribute_ranges.put(key, new HashSet<String>());
                if (type == Type.BOOLEAN) {
                    this.attribute_ranges.get(key).add(Boolean.toString(true));
                    this.attribute_ranges.get(key).add(Boolean.toString(false));
                }
            }
            this.attribute_ranges.get(key).add(val.toString());
        }
        
        int idx = this.attributes.indexOf(key);
        if (!this.txn_values.containsKey(txn_id)) {
            this.txn_values.put(txn_id, new Vector<Object>(this.attributes.size()));
        }
        this.txn_values.get(txn_id).setSize(this.attributes.size());
        this.txn_values.get(txn_id).set(idx, val);
    }