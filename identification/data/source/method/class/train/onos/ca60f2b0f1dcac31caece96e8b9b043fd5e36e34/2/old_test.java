    @Test
    public void encode() {
        Foo f1 = new Foo("foo");
        Foo f2 = new Foo("bar");
        FooCodec codec = new FooCodec();
        ImmutableList<Foo> entities = ImmutableList.of(f1, f2);
        ArrayNode json = codec.encode(entities, new TestContext());
        List<Foo> foos = codec.decode(json, new TestContext());
        assertEquals("incorrect encode/decode", entities, foos);
    }