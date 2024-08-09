public <R extends Geometry> Flowable<Entry<T, S>> search(final R g, final double maxDistance,
            final BiFunction<? super S, ? super R, Double> distance) {
        return search(new Predicate<Geometry>() {
            @Override
            public boolean test(Geometry entry) {
                // just use the mbr initially
                return entry.distance(g.mbr()) < maxDistance;
            }
        })
                // refine with distance function
                .filter(new Predicate<Entry<T, S>>() {
                    @Override
                    public boolean test(Entry<T, S> entry) throws Exception {
                        return distance.apply(entry.geometry(), g) < maxDistance;
                    }
                });
    }