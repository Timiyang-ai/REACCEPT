public static <O> int cardinality(O obj, final Iterable<? super O> coll) {
        if (coll instanceof Set) {
            return (((Set<? super O>) coll).contains(obj) ? 1 : 0);
        }
        if (coll instanceof Bag) {
            return ((Bag<? super O>) coll).getCount(obj);
        }
        int count = 0;
        if (obj == null) {
            for (Object element : coll) {
                if (element == null) {
                    count++;
                }
            }
        } else {
            for (Object element : coll) {
                if (obj.equals(element)) {
                    count++;
                }
            }
        }
        return count;
    }