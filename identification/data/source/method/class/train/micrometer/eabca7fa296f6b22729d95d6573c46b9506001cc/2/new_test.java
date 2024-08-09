    @Test
    void maximumAllowableTags() {
        AtomicInteger n = new AtomicInteger(0);

        MeterFilter filter = MeterFilter.maximumAllowableTags("name", "k", 2, new MeterFilter() {
            @Override
            public MeterFilterReply accept(Meter.Id id) {
                n.incrementAndGet();
                return MeterFilterReply.NEUTRAL;
            }
        });

        Meter.Id id = new Meter.Id("name", Tags.of("k", "1"), null, null, Meter.Type.COUNTER);
        Meter.Id id2 = new Meter.Id("name", Tags.of("k", "2"), null, null, Meter.Type.COUNTER);
        Meter.Id id3 = new Meter.Id("name", Tags.of("k", "3"), null, null, Meter.Type.COUNTER); 
        Meter.Id id4 = new Meter.Id("anotherName", Tags.of("tag", "4"), null, null, Meter.Type.COUNTER);

        filter.accept(id);
        filter.accept(id);
        filter.accept(id2);
        filter.accept(id);
        filter.accept(id3);

        assertThat(n.get()).isEqualTo(1);
        
        filter.accept(id4);
        assertThat(n.get()).isEqualTo(1);
    }