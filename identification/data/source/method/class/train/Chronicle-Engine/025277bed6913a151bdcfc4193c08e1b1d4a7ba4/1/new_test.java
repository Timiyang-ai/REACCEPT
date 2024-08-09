@Test
    public void testGet() throws IOException {
        try (@NotNull ClosableMapSupplier<Integer, String> supplier = map5()) {
            final Map map = supplier.get();
            assertEquals("A", map.get(one));
            try (@NotNull ClosableMapSupplier empty = newStrStrMap()) {
                writeMessage("example of get(<key>) returning null, when the keys is not " +
                        "present in the map");

                yamlLoggger(() -> {
                    Object object = map.get(notPresent);
                    assertNull(object);
                });
            }
        }
    }