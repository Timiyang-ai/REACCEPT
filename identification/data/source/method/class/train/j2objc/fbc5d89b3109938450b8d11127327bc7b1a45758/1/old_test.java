    private void reverseTest(String org, String rev, String back) {
        // create non-shared StringBuilder
        StringBuilder sb = new StringBuilder(org);
        sb.reverse();
        String reversed = sb.toString();
        assertEquals(rev, reversed);
        // create non-shared StringBuilder
        sb = new StringBuilder(reversed);
        sb.reverse();
        reversed = sb.toString();
        assertEquals(back, reversed);

        // test algorithm when StringBuilder is shared
        sb = new StringBuilder(org);
        String copy = sb.toString();
        assertEquals(org, copy);
        sb.reverse();
        reversed = sb.toString();
        assertEquals(rev, reversed);
        sb = new StringBuilder(reversed);
        copy = sb.toString();
        assertEquals(rev, copy);
        sb.reverse();
        reversed = sb.toString();
        assertEquals(back, reversed);
    }