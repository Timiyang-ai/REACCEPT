public static void moveFile( File toMove, File target ) throws IOException
    {
        if ( !toMove.exists() )
        {
            throw new NotFoundException( "Source file[" + toMove.getAbsolutePath()
                    + "] not found" );
        }
        if ( target.exists() )
        {
            throw new NotFoundException( "Target file[" + target.getAbsolutePath()
                    + "] already exists" );
        }

        if ( toMove.renameTo( target ) )
        {
            return;
        }

        if ( toMove.isDirectory() )
        {
            Files.createDirectories( target.toPath() );
            copyRecursively( toMove, target );
            deleteRecursively( toMove );
        }
        else
        {
            copyFile( toMove, target );
            deleteFile( toMove );
        }
    }