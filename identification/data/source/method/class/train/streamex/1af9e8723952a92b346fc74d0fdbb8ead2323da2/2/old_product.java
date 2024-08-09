public <C extends Collection<? super T>> C into(C collection) {
        if (isParallel()) {
            @SuppressWarnings("unchecked")
            List<T> list = Arrays.asList((T[]) toArray());
            collection.addAll(list);
        } else {
            Spliterator<T> spltr = spliterator();
            if (collection instanceof ArrayList) {
                long size = spltr.getExactSizeIfKnown();
                if (size >= 0 && size < Integer.MAX_VALUE - collection.size())
                    ((ArrayList<?>) collection).ensureCapacity((int) (collection.size() + size));
            }
            spltr.forEachRemaining(collection::add);
        }
        return collection;
    }