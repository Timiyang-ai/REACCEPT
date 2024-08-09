public static void show(String title, Table table) throws Exception {

        SwingUtilities.invokeLater(() -> {
            try {
                if (table.column(0) instanceof StringColumn) {
                    initAndShowGUI(title, table.stringColumn(0), table.nCol(1), 640, 480);
                }
                if (table.column(0) instanceof NumberColumn) {
                    initAndShowGUI(title, table.numberColumn(0), table.nCol(1), 640, 480);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }