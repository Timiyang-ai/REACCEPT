@Override
    public TabularDataIngest read(BufferedInputStream stream, File dataFile) throws IOException {
        init();

        if (stream == null) {
            throw new IOException(BundleUtil.getStringFromBundle("ingest.csv.nullStream"));
        }
        TabularDataIngest ingesteddata = new TabularDataIngest();
        DataTable dataTable = new DataTable();

        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(stream));

        File tabFileDestination = File.createTempFile("data-", ".tab");
        PrintWriter tabFileWriter = new PrintWriter(tabFileDestination.getAbsolutePath());

        int lineCount = readFile(localBufferedReader, dataTable, tabFileWriter);

        logger.fine("Tab file produced: " + tabFileDestination.getAbsolutePath());

        dataTable.setUnf("UNF:6:NOTCALCULATED");

        ingesteddata.setTabDelimitedFile(tabFileDestination);
        ingesteddata.setDataTable(dataTable);
        return ingesteddata;

    }