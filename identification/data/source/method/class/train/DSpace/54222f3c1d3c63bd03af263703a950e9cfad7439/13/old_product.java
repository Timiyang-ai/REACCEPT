public Item[] getItems() throws SQLException
    {
        List<Item> items = new ArrayList<Item>();

        // Get items
        TableRowIterator tri = DatabaseManager.queryTable(
        		ourContext, "item",
                "SELECT item.* FROM item, item2bundle WHERE " +
                "item2bundle.item_id=item.item_id AND " +
                "item2bundle.bundle_id= ? ",
                bundleRow.getIntColumn("bundle_id"));

        try
        {
            while (tri.hasNext())
            {
                TableRow r = (TableRow) tri.next();

                // Used cached copy if there is one
                Item fromCache = (Item) ourContext.fromCache(Item.class, r
                        .getIntColumn("item_id"));

                if (fromCache != null)
                {
                    items.add(fromCache);
                }
                else
                {
                    items.add(new Item(ourContext, r));
                }
            }
        }
        finally
        {
            // close the TableRowIterator to free up resources
            if (tri != null)
            {
                tri.close();
            }
        }

        Item[] itemArray = new Item[items.size()];
        itemArray = (Item[]) items.toArray(itemArray);

        return itemArray;
    }