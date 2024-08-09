@Test
    public void testSave() throws Exception {
        List<String> orig_keys = new ArrayList<String>();
        orig_keys.add("KEY1");
        orig_keys.add("KEY2");
        orig_keys.add("KEY3");
        for (String k : orig_keys) {
            Integer val = rand.nextInt();
            this.fset.addFeature(this.txn_trace, k, val);
        } // FOR
        
        String path = "/tmp/fset.txt";
        this.fset.save(path, TARGET_PROCEDURE.getSimpleName());
        String contents = FileUtil.readFile(path);
        assertNotNull(contents);
        assertFalse(contents.isEmpty());

        for (String k : orig_keys) {
            assert(contents.contains(k));
        }
    }