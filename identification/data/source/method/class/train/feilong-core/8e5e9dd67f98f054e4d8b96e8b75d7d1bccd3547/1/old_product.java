public static <O> List<O> remove(Collection<O> objectCollection,O removeElement){
        Collection<O> remove = new ArrayList<O>();
        remove.add(removeElement);
        return removeAll(objectCollection, remove);
    }