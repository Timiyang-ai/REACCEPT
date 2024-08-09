public void save(String path, String name) throws IOException {
        LOG.debug("Writing FeatureSet contents to '" + path + "'");
        
        // Attributes
        FastVector attrs = new FastVector();
        for (Entry<String, Type> e : this.attributes.entrySet()) {
            Attribute a = null;
            
            if (e.getValue() == Type.RANGE) {
                FastVector range_values = new FastVector();
                for (String v : this.attribute_ranges.get(e.getKey())) {
                    range_values.addElement(v);
                } // FOR
                a = new Attribute(e.getKey(), range_values);
            } else {
                a = new Attribute(e.getKey());    
            }
            attrs.addElement(a);
        } // FOR

        Instances data = new Instances(name, attrs, 0);
        
        // Values
        for (Vector<Object> values : this.txn_values.values()) {
            double vals[] = new double[data.numAttributes()];
            for (int i = 0; i < vals.length; i++) {
                Object value = values.get(i);
                Type type = this.attributes.getValue(i);
                
                if (value == null) {
                    vals[i] = Instance.missingValue();
                } else {
                    switch (type) {
                        case NUMERIC:
                            vals[i] = ((Number)value).doubleValue();
                            break;
                        case STRING:
                            vals[i] = data.attribute(i).addStringValue(value.toString());
                            break;
                        case BOOLEAN:
                            vals[i] = data.attribute(i).indexOfValue(Boolean.toString((Boolean)value));
                            break;
                        case RANGE:
                            vals[i] = data.attribute(i).indexOfValue(value.toString());
                            break;
                        default:
                            assert(false) : "Unexpected attribute type " + type;
                    } // SWITCH
                }
                
            }
        } // FOR
        FileWriter out = new FileWriter(path);
    }