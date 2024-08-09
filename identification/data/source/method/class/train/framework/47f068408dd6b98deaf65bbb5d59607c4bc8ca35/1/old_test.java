    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void getFirstSelected_mutliSelectEvent() {
        MultiSelectionEvent<?> event = Mockito.mock(MultiSelectionEvent.class);
        Mockito.doCallRealMethod().when(event).getFirstSelectedItem();

        Mockito.when(event.getValue())
                .thenReturn(new LinkedHashSet(Arrays.asList("foo", "bar")));

        Optional<?> selected = event.getFirstSelectedItem();

        Mockito.verify(event).getValue();
        assertEquals("foo", selected.get());

        Mockito.when(event.getValue()).thenReturn(Collections.emptySet());
        assertFalse(event.getFirstSelectedItem().isPresent());
    }