public List<StringKeyStringValueDto> doGetMetadata( String pathId ) throws FileNotFoundException {
    List<StringKeyStringValueDto> list = null;
    String path = null;
    if ( pathId == null || pathId.equals( FileUtils.PATH_SEPARATOR ) ) {
      path = FileUtils.PATH_SEPARATOR;
    } else {
      if ( !pathId.startsWith( FileUtils.PATH_SEPARATOR ) ) {
        path = idToPath( pathId );
      }
    }
    final RepositoryFileDto file = getRepoWs().getFile( path );
    if ( file == null ) {
      throw new FileNotFoundException();
    }

    list = getRepoWs().getFileMetadata( file.getId() );

    if ( list != null ) {
      boolean hasSchedulable = false;
      for ( StringKeyStringValueDto value : list ) {
        if ( value.getKey().equals( RepositoryFile.SCHEDULABLE_KEY ) ) {
          hasSchedulable = true;
          break;
        }
      }
      if ( !hasSchedulable ) {
        StringKeyStringValueDto schedPerm = new StringKeyStringValueDto( RepositoryFile.SCHEDULABLE_KEY, "true" );
        list.add( schedPerm );
      }

      // check file object for hidden value and add it to the list
      list.add( new StringKeyStringValueDto( RepositoryFile.HIDDEN_KEY, String.valueOf( file.isHidden() ) ) );
    }

    return list;
  }