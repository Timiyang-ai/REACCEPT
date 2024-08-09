public <R extends Geometry> Observable<Entry<T, S>> search(final R g, final double maxDistance,
            final Func2<? super S, ? super R, Double> distance) {
        return search(new Func1<Geometry, Boolean>() {
            @Override
            public Boolean call(Geometry entry) {
                // just use the mbr initially
                return entry.distance(g.mbr()) < maxDistance;
            }
        })
        // refine with distance function
        .filter(new Func1<Entry<T, S>, Boolean>() {
            @Override
            public Boolean call(Entry<T, S> entry) {
                return distance.call(entry.geometry(), g) < maxDistance;
            }
        });
    }