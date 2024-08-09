    @Test
    public void test_convertClassesToClassNames_List() {
        final List<Class<?>> list = new ArrayList<>();
        List<String> result = ClassUtils.convertClassesToClassNames(list);
        assertEquals(0, result.size());

        list.add(String.class);
        list.add(null);
        list.add(Object.class);
        result = ClassUtils.convertClassesToClassNames(list);
        assertEquals(3, result.size());
        assertEquals("java.lang.String", result.get(0));
        assertNull(result.get(1));
        assertEquals("java.lang.Object", result.get(2));

        @SuppressWarnings("unchecked") // test what happens when non-generic code adds wrong type of element
        final List<Object> olist = (List<Object>) (List<?>) list;
        olist.add(new Object());
        assertThrows(ClassCastException.class,
                () -> ClassUtils.convertClassesToClassNames(list),
                "Should not have been able to convert list");
        assertNull(ClassUtils.convertClassesToClassNames(null));
    }