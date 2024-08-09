@Test
    public void testAppend_StringBuilder_int_int() {
        StrBuilder sb = new StrBuilder();
        sb.setNullText("NULL").append((String) null, 0, 1);
        assertEquals("NULL", sb.toString());

        sb = new StrBuilder();
        sb.append(new StringBuilder("foo"), 0, 3);
        assertEquals("foo", sb.toString());

        try {
            sb.append(new StringBuilder("bar"), -1, 1);
            fail("append(StringBuilder, -1,) expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }

        try {
            sb.append(new StringBuilder("bar"), 3, 1);
            fail("append(StringBuilder, 3,) expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }

        try {
            sb.append(new StringBuilder("bar"), 1, -1);
            fail("append(StringBuilder,, -1) expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }

        try {
            sb.append(new StringBuilder("bar"), 1, 3);
            fail("append(StringBuilder, 1, 3) expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }

        try {
            sb.append(new StringBuilder("bar"), -1, 3);
            fail("append(StringBuilder, -1, 3) expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }

        try {
            sb.append(new StringBuilder("bar"), 4, 0);
            fail("append(StringBuilder, 4, 0) expected IndexOutOfBoundsException");
        } catch (final IndexOutOfBoundsException e) {
            // expected
        }

        sb.append(new StringBuilder("bar"), 3, 0);
        assertEquals("foo", sb.toString());

        sb.append(new StringBuilder("abcbardef"), 3, 3);
        assertEquals("foobar", sb.toString());

        sb.append( new StringBuilder("abcbardef"), 4, 3);
        assertEquals("foobarard", sb.toString());
    }