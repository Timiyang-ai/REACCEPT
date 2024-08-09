public boolean containsId(Object itemId) {
        if (itemId == null) {
            return false;
        }

        if (cachedItems.containsKey(itemId)) {
            return true;
        } else {
            for (RowItem item : addedItems) {
                if (item.getId().equals(itemId)) {
                    return itemPassesFilters(item);
                }
            }
        }
        if (removedItems.containsKey(itemId)) {
            return false;
        }

        if (itemId instanceof ReadOnlyRowId) {
            int rowNum = ((ReadOnlyRowId) itemId).getRowNum();
            return rowNum >= 0 && rowNum < size;
        }

        if (!(itemId instanceof TemporaryRowId)) {
            try {
                return delegate.containsRowWithKey(((RowId) itemId).getId());
            } catch (Exception e) {
                /* Query failed, just return false. */
                debug(e, null);
            }
        }
        return false;
    }