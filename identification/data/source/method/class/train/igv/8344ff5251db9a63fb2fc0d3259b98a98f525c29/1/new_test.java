    @Test
    public void testgetAminoAcidByName() throws Exception {
        Map<String, String> expected = new HashMap<String, String>();
        expected.put("His", "Histidine");
        expected.put("Thr", "Threonine");
        expected.put("Asn", "Asparagine");
        expected.put("R", "Arginine");
        expected.put("K", "Lysine");
        expected.put("V", "Valine");
        for (String name : expected.keySet()) {
            String exp = expected.get(name);
            String act = AminoAcidManager.getAminoAcidByName(name).getFullName();
            assertEquals(exp, act);
        }
    }