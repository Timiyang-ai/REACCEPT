public void setSortOrder(List<SortOrder> order) {
        sortOrder.clear();
        if (order != null) {
            sortOrder.addAll(order);
        }
        sort();
    }