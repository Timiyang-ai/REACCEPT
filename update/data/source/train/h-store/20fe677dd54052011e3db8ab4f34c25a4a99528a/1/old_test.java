@Test
    public void testGetProcedureHistogram() throws Exception {
        Histogram h = WorkloadUtil.getProcedureHistogram(workload_file);
        assertNotNull(h);
        assert(h.getSampleCount() > 0);
        
        for (Procedure catalog_proc : catalog_db.getProcedures()) {
            String proc_name = catalog_proc.getName();
            if (catalog_proc.getSystemproc() || ignore.contains(proc_name)) continue;
            assert(h.get(proc_name, 0) > 0) : "No Entries for " + proc_name;
        } // FOR
        
        System.err.println(h);
    }