public List<Community> getSubcommunities()
    {
        Collections.sort(subCommunities, new NameAscendingComparator());
        return subCommunities;
    }