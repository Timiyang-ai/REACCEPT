public final void bug15375(int num) throws Exception {
        Workbook wb1 = _testDataProvider.createWorkbook();
        Sheet sheet = wb1.createSheet();
        CreationHelper factory = wb1.getCreationHelper();

        String tmp1 = null;
        String tmp2 = null;
        String tmp3 = null;

        for (int i = 0; i < num; i++) {
            tmp1 = "Test1" + i;
            tmp2 = "Test2" + i;
            tmp3 = "Test3" + i;

            Row row = sheet.createRow(i);

            Cell cell = row.createCell(0);
            cell.setCellValue(factory.createRichTextString(tmp1));
            cell = row.createCell(1);
            cell.setCellValue(factory.createRichTextString(tmp2));
            cell = row.createCell(2);
            cell.setCellValue(factory.createRichTextString(tmp3));
        }
        Workbook wb2 = _testDataProvider.writeOutAndReadBack(wb1);
        wb1.close();

        sheet = wb2.getSheetAt(0);
        for (int i = 0; i < num; i++) {
            tmp1 = "Test1" + i;
            tmp2 = "Test2" + i;
            tmp3 = "Test3" + i;

            Row row = sheet.getRow(i);

            assertEquals(tmp1, row.getCell(0).getStringCellValue());
            assertEquals(tmp2, row.getCell(1).getStringCellValue());
            assertEquals(tmp3, row.getCell(2).getStringCellValue());
        }
        wb2.close();
    }