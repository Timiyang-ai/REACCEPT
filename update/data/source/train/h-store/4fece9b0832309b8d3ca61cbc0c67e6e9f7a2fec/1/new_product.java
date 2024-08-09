public synchronized void addFeature(TransactionTrace txn, String key, Object val, Type type) {
        final boolean trace = LOG.isTraceEnabled();
        final boolean debug = LOG.isDebugEnabled();
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
            if (debug) LOG.debug("Adding new attribute " + key + " [" + type + "]");
            this.attributes.put(key, type);
        }
        
        // Store ranges if needed
        if (type == Type.RANGE || type == Type.BOOLEAN) {
            if (!this.attribute_ranges.containsKey(key)) {
                this.attribute_ranges.put(key, new TreeSet<String>());
                if (type == Type.BOOLEAN) {
                    this.attribute_ranges.get(key).add(Boolean.toString(true));
                    this.attribute_ranges.get(key).add(Boolean.toString(false));
                }
            }
            this.attribute_ranges.get(key).add(val.toString());
        }
        
        int idx = this.attributes.indexOf(key);
        int num_attributes = this.attributes.size();
        Vector<Object> values = this.txn_values.get(txn_id); 
        if (values == null) {
            if (trace) LOG.trace("Creating new feature vector for " + txn_id);
            values = new Vector<Object>(num_attributes);
            values.setSize(num_attributes);
            this.txn_values.put(txn_id, values);
        }
        if (num_attributes != this.last_num_attributes) {
            assert(num_attributes > this.last_num_attributes);
            for (Vector<Object> v : this.txn_values.values()) {
                v.setSize(num_attributes);
            } // FOR
            this.last_num_attributes = num_attributes;
            if (debug) LOG.debug("Increased FeatureSet size to " + this.last_num_attributes + " attributes");
        }
        this.txn_values.get(txn_id).set(idx, val);
        if (trace) LOG.trace(txn_id + ": " + key + " => " + val);
    }