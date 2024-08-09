public synchronized void addFeature(TransactionTrace txn, String key, Object val, Type type) {
        long txn_id = txn.getTransactionId();
        
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
            if (debug.get()) LOG.debug("Adding new attribute " + key + " [" + type + "]");
            this.attributes.put(key, type);
            this.attribute_histograms.put(key, new Histogram());
            this.attribute_types.put(key, VoltType.NULL);
        }
        // HACK
        if (val != null && (val.getClass().equals(int.class) || val.getClass().equals(Integer.class))) {
            val = new Long((Integer)val);
        }
        
        // Always store the values in a histogram so we can normalize them later on
        try {
            this.attribute_histograms.get(key).put(val);
        } catch (Exception ex) {
            LOG.error("\n" + this.attribute_histograms.get(key));
            LOG.error("Invalid value '" + val + "' for attribute '" + key + "'", ex);
            System.exit(1);
        }

        // Now add the values into this txn's feature vector
        int idx = this.attributes.indexOf(key);
        int num_attributes = this.attributes.size();
        Vector<Object> values = this.txn_values.get(txn_id); 
        if (values == null) {
            if (trace.get()) LOG.trace("Creating new feature vector for " + txn_id);
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
            if (trace.get()) LOG.trace("Increased FeatureSet size to " + this.last_num_attributes + " attributes");
        }
        this.txn_values.get(txn_id).set(idx, val);
        
        if (val != null && this.attribute_types.get(key) == VoltType.NULL) {
            this.attribute_types.put(key, VoltType.typeFromClass(val.getClass()));
        }
        
        if (trace.get()) LOG.trace(txn_id + ": " + key + " => " + val);
    }