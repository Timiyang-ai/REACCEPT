public <R extends Geometry> Flowable<Entry<T, S>> search(final R g,
            final BiFunction<? super S, ? super R, Boolean> intersects) {
        return search(g.mbr()).filter(new Predicate<Entry<T, S>>() {
            @Override
            public boolean test(Entry<T, S> entry) throws Exception {
                return intersects.apply(entry.geometry(), g);
            }
        });
    }