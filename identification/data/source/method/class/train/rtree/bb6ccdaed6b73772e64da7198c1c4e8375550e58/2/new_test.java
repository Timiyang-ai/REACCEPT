    private static <T extends Geometry> Subscriber<Entry<Object, T>> createBackpressureSubscriber(
            final Collection<Entry<Object, T>> collection) {
        return new Subscriber<Entry<Object, T>>() {

            @Override
            public void onStart() {
                request(1);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Entry<Object, T> t) {
                collection.add(t);
                request(1);
            }
        };
    }