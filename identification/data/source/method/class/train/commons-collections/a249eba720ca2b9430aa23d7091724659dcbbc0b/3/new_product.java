public static int cardinality(Object obj, final Collection coll) {
        if (coll instanceof Set) {
            return (coll.contains(obj) ? 1 : 0);
        }
        if (coll instanceof Bag) {
            return ((Bag) coll).getCount(obj);
        }
        int count = 0;
        if (obj == null) {
            for (Iterator it = coll.iterator();it.hasNext();) {
                if (it.next() == null) {
                    count++;
                }
            }
        } else {
            for (Iterator it = coll.iterator();it.hasNext();) {
                if (obj.equals(it.next())) {
                    count++;
                }
            }
        }
        return count;
    }