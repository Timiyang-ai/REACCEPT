@Test
    public void testAdd() {
        String[] values = null;
        Metadata meta = new Metadata();

        values = meta.getValues(CONTENTTYPE);
        assertEquals(0, values.length);

        meta.add(CONTENTTYPE, "value1");
        values = meta.getValues(CONTENTTYPE);
        assertEquals(1, values.length);
        assertEquals("value1", values[0]);

        meta.add(CONTENTTYPE, "value2");
        values = meta.getValues(CONTENTTYPE);
        assertEquals(2, values.length);
        assertEquals("value1", values[0]);
        assertEquals("value2", values[1]);

        // NOTE : For now, the same value can be added many times.
        // Should it be changed?
        meta.add(CONTENTTYPE, "value1");
        values = meta.getValues(CONTENTTYPE);
        assertEquals(3, values.length);
        assertEquals("value1", values[0]);
        assertEquals("value2", values[1]);
        assertEquals("value1", values[2]);
        
        Property nonMultiValued = Property.internalText("nonMultiValued");
        meta.add(nonMultiValued, "value1");
        try {
            meta.add(nonMultiValued, "value2");
            fail("add should fail on the second call of a non-multi valued item");
        } catch (PropertyTypeException e) {
        }
    }