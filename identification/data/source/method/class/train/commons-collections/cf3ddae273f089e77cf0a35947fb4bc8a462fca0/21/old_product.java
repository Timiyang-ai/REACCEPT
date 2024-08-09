public static void selectRejected(Collection inputCollection, Predicate predicate, Collection outputCollection) {
        if (inputCollection != null && predicate != null) {
            for (Iterator iter = inputCollection.iterator(); iter.hasNext();) {
                Object item = iter.next();
                if (predicate.evaluate(item) == false) {
                    outputCollection.add(item);
                }
            }
        }
    }