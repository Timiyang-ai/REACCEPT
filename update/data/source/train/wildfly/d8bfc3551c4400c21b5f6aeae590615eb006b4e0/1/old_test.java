@Test
    public void get() throws Exception {
        GUID guid = new GUID();
        SimpleMarshalledValue<GUID> mv = this.factory.createMarshalledValue(guid);

        assertNotNull(mv.peek());
        assertSame(guid, mv.peek());
        assertSame(guid, mv.get(this.context));

        SimpleMarshalledValue<GUID> copy = replicate(mv);

        assertNull(copy.peek());
        
        GUID guid2 = copy.get(this.context);
        assertNotSame(guid, guid2);
        assertEquals(guid, guid2);

        copy = replicate(copy);
        guid2 = copy.get(this.context);
        assertEquals(guid, guid2);

        mv = this.factory.createMarshalledValue(null);
        assertNull(mv.peek());
        assertNull(mv.getBytes());
        assertNull(mv.get(this.context));
    }