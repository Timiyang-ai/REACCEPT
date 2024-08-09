public final void bug15375(int num) {
        Workbook wb = _testDataProvider.createWorkbook();
        Sheet sheet = wb.createSheet();
        CreationHelper factory = wb.getCreationHelper();

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
        wb = _testDataProvider.writeOutAndReadBack(wb);
        for (int i = 0; i < num; i++) {
            tmp1 = "Test1" + i;
            tmp2 = "Test2" + i;
            tmp3 = "Test3" + i;

            Row row = sheet.getRow(i);

            assertEquals(tmp1, row.getCell(0).getStringCellValue());
            assertEquals(tmp2, row.getCell(1).getStringCellValue());
            assertEquals(tmp3, row.getCell(2).getStringCellValue());
        }
    }