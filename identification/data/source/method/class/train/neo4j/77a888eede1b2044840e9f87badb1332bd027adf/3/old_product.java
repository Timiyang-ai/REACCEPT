public static File moveFile( File toMove, File targetDirectory )
    {
        if ( !targetDirectory.isDirectory() )
        {
            throw new IllegalArgumentException(
                    "Move target must be a directory, not " + targetDirectory );
        }
        String oldName = toMove.getName();
        File endFile = new File( targetDirectory, oldName );
        return renameFile( toMove, endFile ) ? toMove : null;
    }