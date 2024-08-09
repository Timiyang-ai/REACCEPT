@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "readResolve",
        args = {}
    )
    @KnownFailure("readResolve does not work properly")
    public void test_readResolve() {
        // test for method java.lang.Object readResolve()

        // see serialization stress tests:
        // implemented in
        // SerializationStressTest4.test_writeObject_NumberFormat_Field()

        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bytes);

            DateFormat.Field dfield, dfield2;
            MyField field;

            // a regular instance of DateFormat.Field
            dfield = DateFormat.Field.MILLISECOND;

            // a subclass instance with null name
            field = new MyField(null, Calendar.AM_PM);

            out.writeObject(dfield);
            out.writeObject(field);

            in = new ObjectInputStream(new ByteArrayInputStream(bytes
                    .toByteArray()));

            try {
                dfield2 = (Field) in.readObject();
                assertSame("resolved incorrectly", dfield, dfield2);
            } catch (IllegalArgumentException e) {
                fail("Unexpected IllegalArgumentException: " + e);
            }

            try {
                in.readObject();
                fail("Expected InvalidObjectException for subclass instance with null name");
            } catch (InvalidObjectException e) {
            }

        } catch (IOException e) {
            fail("unexpected IOException" + e);
        } catch (ClassNotFoundException e) {
            fail("unexpected ClassNotFoundException" + e);
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }