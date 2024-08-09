public <C extends Collection<? super T>> C into(C collection) {
        if (isParallel()) {
            @SuppressWarnings("unchecked")
            List<T> list = Arrays.asList((T[]) toArray());
            collection.addAll(list);
        } else {
            Spliterator<T> spliterator = spliterator();
            prepareToAdd(collection, spliterator);
            spliterator.forEachRemaining(collection::add);
        }
        return collection;
    }