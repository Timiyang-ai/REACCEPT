public void parseAssetFolderDefinitions(ActionManager manager) throws Exception {
        manager.withResolver(rr -> {
            final XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            // Close the InputStream to prevent resource leaks.
            excelFile.close();

            final XSSFSheet sheet = workbook.getSheetAt(0);
            final Iterator<Row> rows = sheet.rowIterator();

            while(rows.hasNext()) {
                parseAssetFolderRow(rows.next());
            }
            log.info("Finished Parsing and collected [ {} ] asset folders for creation.", assetFolderDefinitions.size());
        });
    }