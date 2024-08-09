public Instances export(String name) {
        // Attributes
        FastVector attrs = new FastVector();
        for (Entry<String, Type> e : this.attributes.entrySet()) {
            Attribute a = null;
            
            switch (e.getValue()) {
                case RANGE:
                case BOOLEAN: {
                    FastVector range_values = new FastVector();
                    for (String v : this.attribute_ranges.get(e.getKey())) {
                        range_values.addElement(v);
                    } // FOR
                    a = new Attribute(e.getKey(), range_values);
                    break;
                }
                case STRING:
                    a = new Attribute(e.getKey(), (FastVector)null);
                    break;
                default:
                    a = new Attribute(e.getKey());       
            } // SWITCH
            attrs.addElement(a);
        } // FOR

        Instances data = new Instances(name, attrs, 0);
        
        // Instance Values
        for (Vector<Object> values : this.txn_values.values()) {
            double instance[] = new double[data.numAttributes()];
            for (int i = 0; i < instance.length; i++) {
                Object value = values.get(i);
                Type type = this.attributes.getValue(i);
                
                if (value == null) {
                    instance[i] = Instance.missingValue();
                } else {
                    switch (type) {
                        case NUMERIC:
                            instance[i] = ((Number)value).doubleValue();
                            break;
                        case STRING:
                            instance[i] = data.attribute(i).addStringValue(value.toString());
                            break;
                        case BOOLEAN:
                            instance[i] = data.attribute(i).indexOfValue(Boolean.toString((Boolean)value));
                            break;
                        case RANGE:
                            instance[i] = data.attribute(i).indexOfValue(value.toString());
                            break;
                        default:
                            assert(false) : "Unexpected attribute type " + type;
                    } // SWITCH
                }
            } // FOR
            data.add(new Instance(1.0, instance));
        } // FOR
        
        return (data);
    }