public static int cardinality(Object obj, final Collection col) {
        int count = 0;
        if(null == obj) {
            for(Iterator it = col.iterator();it.hasNext();) {
                if(null == it.next()) {
                    count++;
                }
            }
        } else {
            for(Iterator it = col.iterator();it.hasNext();) {
                if(obj.equals(it.next())) {
                    count++;
                }
            }
        }
        return count;
    }