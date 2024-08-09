    @Test
    public void changeField() {
        AllTypesContainer reference = new AllTypesContainer();
        AllTypesContainer changed = new AllTypesContainer();
        assertTrue(reference.equals(changed));

        for (Field field : FieldIterable.of(AllTypesContainer.class)) {
            new FieldAccessor(changed, field).changeField(prefabValues, TypeTag.NULL);
            assertFalse("On field: " + field.getName(), reference.equals(changed));
            new FieldAccessor(reference, field).changeField(prefabValues, TypeTag.NULL);
            assertTrue("On field: " + field.getName(), reference.equals(changed));
        }
    }