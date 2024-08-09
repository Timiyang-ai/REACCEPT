public static CollectionProperty tableModelToCollectionProperty(PowerTableModel model, String propname) {
        CollectionProperty rows = new CollectionProperty(propname, new ArrayList<Object>());
        for (int col = 0; col < model.getColumnCount(); col++) {
            rows.addItem(model.getColumnData(model.getColumnName(col)));
        }
        return rows;
    }