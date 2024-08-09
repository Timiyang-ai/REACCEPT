@Test
    public void removeMergedRegions() throws IOException {
        Workbook wb = _testDataProvider.createWorkbook();
        Sheet sheet = wb.createSheet();
        
        Map<Integer, CellRangeAddress> mergedRegions = new HashMap<>();
        for (int r=0; r<10; r++) {
            CellRangeAddress region = new CellRangeAddress(r, r, 0, 1);
            mergedRegions.put(r, region);
            sheet.addMergedRegion(region);
        }
        assertCollectionEquals(mergedRegions.values(), sheet.getMergedRegions());
        
        Collection<Integer> removed = Arrays.asList(0, 2, 3, 6, 8);
        mergedRegions.keySet().removeAll(removed);
        sheet.removeMergedRegions(removed);
        assertCollectionEquals(mergedRegions.values(), sheet.getMergedRegions());
        
        wb.close();
    }