    @SuppressWarnings({ "unchecked", "serial" })
    @Test
    public void addValueChangeListener() {
        AtomicReference<MultiSelectionListener<String>> selectionListener = new AtomicReference<>();
        Registration registration = Mockito.mock(Registration.class);
        Set<String> set = new HashSet<>();
        set.add("foo");
        set.add("bar");
        AbstractMultiSelect<String> select = new AbstractMultiSelect<String>() {
            @Override
            public Registration addSelectionListener(
                    MultiSelectionListener<String> listener) {
                selectionListener.set(listener);
                return registration;
            }

            @Override
            public Set<String> getValue() {
                return set;
            }

            @Override
            public void setItems(Collection<String> items) {
                throw new UnsupportedOperationException(
                        "Not implemented for this test");
            }

            @Override
            public DataProvider<String, ?> getDataProvider() {
                return null;
            }
        };

        AtomicReference<ValueChangeEvent<?>> event = new AtomicReference<>();
        Registration actualRegistration = select.addValueChangeListener(evt -> {
            assertNull(event.get());
            event.set(evt);
        });

        assertSame(registration, actualRegistration);

        selectionListener.get().selectionChange(new MultiSelectionEvent<>(
                select, Mockito.mock(Set.class), true));

        assertEquals(select, event.get().getComponent());
        assertEquals(set, event.get().getValue());
        assertTrue(event.get().isUserOriginated());
    }