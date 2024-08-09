public static <O> List<O> remove(Collection<O> objectCollection,O removeElement){
        return removeAll(objectCollection, ConvertUtil.toList(removeElement));
    }