public <R extends Geometry> Observable<Entry<T, S>> search(final R g,
            final Func2<? super S, ? super R, Boolean> intersects) {
        return search(g.mbr()).filter(new Func1<Entry<T, S>, Boolean>() {
            @Override
            public Boolean call(Entry<T, S> entry) {
                return intersects.call(entry.geometry(), g);
            }
        });
    }