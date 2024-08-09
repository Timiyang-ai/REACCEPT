@Test
    public void testGetProvider() {
        final Hints.Key    key      = DummyFactory.DUMMY_FACTORY;
        final DummyFactory factory1 = new DummyFactory.Example1();
        final DummyFactory factory2 = new DummyFactory.Example2();
        final DummyFactory factory3 = new DummyFactory.Example3();
        final FactoryRegistry registry = getRegistry(false, factory1, factory2, factory3);
        Hints hints;
        DummyFactory factory;
        // ------------------------------------------------
        //     PART 1: SIMPLE HINT (not a Factory hint)
        // ------------------------------------------------
        /*
         * No hints. The fist factory should be selected.
         */
        hints   = null;
        factory = registry.getServiceProvider(DummyFactory.class, null, hints, key);
        assertSame("No preferences; should select the first factory. ", factory1, factory);
        /*
         * A hint compatible with one of our factories. Factory #1 declares explicitly that it uses
         * a bilinear interpolation, which is compatible with user's hints. All other factories are
         * indifferent. Since factory #1 is the first one in the list, it should be selected.
         */
        hints   = new Hints(Hints.KEY_INTERPOLATION, Hints.VALUE_INTERPOLATION_BILINEAR);
        factory = registry.getServiceProvider(DummyFactory.class, null, hints, key);
        assertSame("First factory matches; it should be selected. ", factory1, factory);
        /*
         * A hint incompatible with all our factories. Factory #1 is the only one to defines
         * explicitly a KEY_INTERPOLATION hint, but all other factories depend on factory #1
         * either directly (factory #2) or indirectly (factory #3, which depends on #2).
         */
        hints = new Hints(Hints.KEY_INTERPOLATION, Hints.VALUE_INTERPOLATION_BICUBIC);
        try {
            factory = registry.getServiceProvider(DummyFactory.class, null, hints, key);
            fail("Found factory "+factory+", while the hint should have been rejected.");
        } catch (FactoryNotFoundException exception) {
            // This is the expected exception. Continue...
        }
        /*
         * Add a new factory implementation, and try again with exactly the same hints
         * than the previous test. This time, the new factory should be selected since
         * this one doesn't have any dependency toward factory #1.
         */
        final DummyFactory factory4 = new DummyFactory.Example4();
        registry.registerServiceProvider(factory4);
        assertTrue(registry.setOrdering(DummyFactory.class, factory1, factory4));
        factory = registry.getServiceProvider(DummyFactory.class, null, hints, key);
        assertSame("The new factory should be selected. ", factory4, factory);

        // ----------------------------
        //     PART 2: FACTORY HINT
        // ----------------------------
        /*
         * Trivial case: user gives explicitly a factory instance.
         */
        DummyFactory explicit = new DummyFactory.Example3();
        hints   = new Hints(DummyFactory.DUMMY_FACTORY, explicit);
        factory = registry.getServiceProvider(DummyFactory.class, null, hints, key);
        assertSame("The user-specified factory should have been selected. ", explicit, factory);
        /*
         * User specifies the expected implementation class rather than an instance.
         */
        hints   = new Hints(DummyFactory.DUMMY_FACTORY, DummyFactory.Example2.class);
        factory = registry.getServiceProvider(DummyFactory.class, null, hints, key);
        assertSame("Factory of class #2 were requested. ", factory2, factory);
        /*
         * Same as above, but with classes specified in an array.
         */
        hints = new Hints(DummyFactory.DUMMY_FACTORY, new Class<?>[] {
            DummyFactory.Example3.class,
            DummyFactory.Example2.class
        });
        factory = registry.getServiceProvider(DummyFactory.class, null, hints, key);
        assertSame("Factory of class #3 were requested. ", factory3, factory);
        /*
         * The following hint should be ignored by factory #1, since this factory doesn't have
         * any dependency to the INTERNAL_FACTORY hint. Since factory #1 is first in the ordering,
         * it should be selected.
         */
        hints   = new Hints(DummyFactory.INTERNAL_FACTORY, DummyFactory.Example2.class);
        factory = registry.getServiceProvider(DummyFactory.class, null, hints, key);
        assertSame("Expected factory #1. ", factory1, factory);
        /*
         * If the user really wants some factory that do have a dependency to factory #2, he should
         * specifies in a DUMMY_FACTORY hint the implementation classes (or a common super-class or
         * interface) that do care about the INTERNAL_FACTORY hint. Note that this extra step should
         * not be a big deal in most real application, because:
         *
         *  1) Either all implementations have this dependency (for example it would be
         *     unusual to see a DatumAuthorityFactory without a DatumFactory dependency);
         *
         *  2) or the user really know the implementation he wants (for example if he specifies a
         *     JTS CoordinateSequenceFactory, he probably wants to use the JTS GeometryFactory).
         *
         * In the particular case of this test suite, this extra step would not be needed
         * neither if factory #1 was last in the ordering rather than first.
         */
        final Hints implementations = new Hints(DummyFactory.DUMMY_FACTORY, new Class[]
                                      {DummyFactory.Example2.class, DummyFactory.Example3.class});
        /*
         * Now search NOT for factory #1, but rather for a factory using #1 internally.
         * This is the case of factory #2.
         */
        hints = new Hints(DummyFactory.INTERNAL_FACTORY, DummyFactory.Example1.class);
        hints.add(implementations);
        factory = registry.getServiceProvider(DummyFactory.class, null, hints, key);
        assertSame("Expected a factory using #1 internally. ", factory2, factory);
    }