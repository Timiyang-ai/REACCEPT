public static void show(String title, NumericSummaryTable table) throws Exception {

        SwingUtilities.invokeLater(() -> {
            try {
                if (table.column(0) instanceof CategoryColumn) {
                    initAndShowGUI(title, table.categoryColumn(0), table.nCol(1), 640, 480);
                }
                if (table.column(0) instanceof ShortColumn) {
                    initAndShowGUI(title, table.shortColumn(0), table.nCol(1), 640, 480);
                }
                if (table.column(0) instanceof IntColumn) {
                    initAndShowGUI(title, table.intColumn(0), table.nCol(1), 640, 480);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }