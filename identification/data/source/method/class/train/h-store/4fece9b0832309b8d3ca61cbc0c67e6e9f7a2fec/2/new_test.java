@Test
    public void testAddFeature() throws Exception {
        List<String> orig_keys = new ArrayList<String>();
        orig_keys.add("KEY1");
        orig_keys.add("KEY2");
        orig_keys.add("KEY3");
        for (String k : orig_keys) {
            Integer val = rand.nextInt();
            this.fset.addFeature(this.txn_trace, k, val);
        } // FOR
        
        List<String> features = fset.getFeatures();
        assert(features.isEmpty() == false);
        assertEquals(orig_keys.size(), features.size());
        assertEquals(orig_keys, features);
    }