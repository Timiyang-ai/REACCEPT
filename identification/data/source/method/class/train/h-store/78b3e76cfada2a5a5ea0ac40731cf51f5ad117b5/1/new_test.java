@Test
    public void testExport() throws Exception {
        List<String> orig_keys = new ArrayList<String>();
        orig_keys.add("KEY1");
        orig_keys.add("KEY2");
        orig_keys.add("KEY3");
        for (String k : orig_keys) {
            Integer val = rand.nextInt();
            this.fset.addFeature(this.txn_trace, k, val);
        } // FOR

        Instances data = this.fset.export(TARGET_PROCEDURE.getSimpleName());
        assertNotNull(data);
        String contents = data.toString();
        assertNotNull(contents);
        assertFalse(contents.isEmpty());

        for (String k : orig_keys) {
            assert(contents.contains(k));
        }
    }