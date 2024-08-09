public List<Collection> getCollections()
    {
        Collections.sort(collections, new NameAscendingComparator());
        return collections;
    }