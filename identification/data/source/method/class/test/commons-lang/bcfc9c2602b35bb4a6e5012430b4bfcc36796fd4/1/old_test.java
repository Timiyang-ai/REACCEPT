    @Test
    public void test_convertClassNamesToClasses_List() {
        final List<String> list = new ArrayList<>();
        List<Class<?>> result = ClassUtils.convertClassNamesToClasses(list);
        assertEquals(0, result.size());

        list.add("java.lang.String");
        list.add("java.lang.xxx");
        list.add("java.lang.Object");
        result = ClassUtils.convertClassNamesToClasses(list);
        assertEquals(3, result.size());
        assertEquals(String.class, result.get(0));
        assertNull(result.get(1));
        assertEquals(Object.class, result.get(2));

        @SuppressWarnings("unchecked") // test what happens when non-generic code adds wrong type of element
        final List<Object> olist = (List<Object>) (List<?>) list;
        olist.add(new Object());
        assertThrows(ClassCastException.class,
                () -> ClassUtils.convertClassNamesToClasses(list),
                "Should not have been able to convert list");
        assertNull(ClassUtils.convertClassNamesToClasses(null));
    }