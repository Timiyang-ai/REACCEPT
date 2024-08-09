public static CollectionProperty tableModelRowsToCollectionProperty(PowerTableModel model, String propname) {
        CollectionProperty rows = new CollectionProperty(propname, new ArrayList<Object>());
        for (int row = 0; row < model.getRowCount(); row++) {
            List<Object> item = getArrayListForArray(model.getRowData(row));
            rows.addItem(item);
        }
        return rows;
    }