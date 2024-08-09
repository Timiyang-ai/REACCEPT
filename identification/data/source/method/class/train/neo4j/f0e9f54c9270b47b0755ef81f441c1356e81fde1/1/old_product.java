public List<File> listFolders()
    {
        File[] files = fileSystem.listFiles( getIndexFolder() );
        return files == null ? Collections.emptyList()
                             : Stream.of( files ).filter( fileSystem::isDirectory ).collect( toList() );

    }