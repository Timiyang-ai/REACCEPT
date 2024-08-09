@Test
    public void testSetHeader() {
        // check invalid header passes
        final RegexpHeaderCheck instance = new RegexpHeaderCheck();
        try {
            final String header = "^/**\\n * Licensed to the Apache Software Foundation (ASF)";
            instance.setHeader(header);
            fail(String.format(Locale.ROOT, "%s should have been thrown",
                    IllegalArgumentException.class));
        }
        catch (IllegalArgumentException ex) {
            assertEquals("Unable to parse format: ^/**\\n *"
                    + " Licensed to the Apache Software Foundation (ASF)",
                    ex.getMessage());
        }
    }