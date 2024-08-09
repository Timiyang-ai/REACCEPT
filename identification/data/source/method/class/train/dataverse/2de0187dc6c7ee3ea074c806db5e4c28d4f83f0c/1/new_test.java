    @Test
    public void testfindAll() {
        DataFile dataFile = new DataFile();
        dataFile.setId(42l);
        FileMetadata fmd = new FileMetadata();
        fmd.setId(2L);
        DatasetVersion dv = new DatasetVersion();
        Dataset ds = new Dataset();
        dv.setDataset(ds);
        fmd.setDatasetVersion(dv);
        List<FileMetadata> fmdl = new ArrayList<FileMetadata>();
        fmdl.add(fmd);
        dataFile.setFileMetadatas(fmdl);
        List<DataTable> dataTables = new ArrayList<DataTable>();
        dataTables.add(new DataTable());
        dataFile.setDataTables(dataTables);
        ApiToken apiToken = new ApiToken();
        apiToken.setTokenString("7196b5ce-f200-4286-8809-03ffdbc255d7");
        ExternalTool.Type type = ExternalTool.Type.EXPLORE;
        ExternalTool.Scope scope = ExternalTool.Scope.FILE;
        ExternalTool externalTool = new ExternalTool("displayName", "description", type, scope, "http://foo.com", "{}", DataFileServiceBean.MIME_TYPE_TSV_ALT);
        ExternalToolHandler externalToolHandler4 = new ExternalToolHandler(externalTool, dataFile, apiToken, fmd, null);
        List<ExternalTool> externalTools = new ArrayList<>();
        externalTools.add(externalTool);
        List<ExternalTool> availableExternalTools = ExternalToolServiceBean.findExternalToolsByFile(externalTools, dataFile);
        assertEquals(availableExternalTools.size(), 1);
    }