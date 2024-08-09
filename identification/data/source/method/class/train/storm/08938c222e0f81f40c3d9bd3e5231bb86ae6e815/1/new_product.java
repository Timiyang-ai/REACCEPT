public List<Object> select(Fields selector, List<Object> tuple) {
        List<Object> ret = new ArrayList<>(selector.size());
        for (String s : selector) {
            ret.add(tuple.get(fieldIndex(s))); 
        }
        return ret;
    }