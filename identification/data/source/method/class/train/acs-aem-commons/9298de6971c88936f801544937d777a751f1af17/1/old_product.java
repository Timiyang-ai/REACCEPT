public void parseAssetFolderDefinitions(ActionManager manager) throws Exception {
        manager.withResolver(rr -> {
            final XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            // Close the inputstream to prevent resource leakage
            excelFile.close();

            final XSSFSheet sheet = workbook.getSheetAt(0);
            final Iterator<Row> rows = sheet.rowIterator();

            while(rows.hasNext()) {
                final Row row = rows.next();
                final Iterator<Cell> cells = row.cellIterator();

                // The previousAssetFolderPath is reset on each new row.
                String previousAssetFolderPath = null;

                while (cells.hasNext()) {
                    final Cell cell = cells.next();

                    final String cellValue = StringUtils.trimToNull(cell.getStringCellValue());
                    if (StringUtils.isBlank(cellValue)) {
                        // Hitting a blank cell means its the end of this row; don't process anything past this
                        break;
                    }

                    // Generate a asset folder definition that will in turn be used to drive the asset folder definition creation
                    AssetFolderDefinition assetFolderDefinition = getAssetFolderDefinition(primary, cellValue, previousAssetFolderPath);

                    if (assetFolderDefinition == null) {
                        assetFolderDefinition = getAssetFolderDefinition(fallback, cellValue, previousAssetFolderPath);
                    }

                    if (assetFolderDefinition == null) {
                        log.warn("Could not find a Asset Folder Converter that accepts value [ {} ]; skipping...", cellValue);
                        // Record parse failure
                        record(ReportRowSatus.FAILED_TO_PARSE, "", cellValue);
                        // Break to next Row
                        break;
                    } else {
                        /* Prepare for next Cell */
                        previousAssetFolderPath = assetFolderDefinition.getPath();

                        if (assetFolderDefinitions.get(assetFolderDefinition.getId()) == null) {
                            assetFolderDefinitions.put(assetFolderDefinition.getId(), assetFolderDefinition);
                        }
                    }
                }
            };
            log.info("Finished Parsing and collected [ {} ] asset folders for creation.", assetFolderDefinitions.size());
        });
    }