public void doSetMetadata( String pathId, List<StringKeyStringValueDto> metadata ) throws GeneralSecurityException {
    RepositoryFileDto file = getRepoWs().getFile( idToPath( pathId ) );
    RepositoryFileAclDto fileAcl = getRepoWs().getAcl( file.getId() );

    boolean canManage =
      getSession().getName().equals( fileAcl.getOwner() )
        || ( getPolicy().isAllowed( RepositoryReadAction.NAME )
        && getPolicy().isAllowed( RepositoryCreateAction.NAME ) && getPolicy().isAllowed(
          AdministerSecurityAction.NAME ) );

    if ( !canManage ) {

      if ( fileAcl.isEntriesInheriting() ) {
        List<RepositoryFileAclAceDto> aces = getRepoWs().getEffectiveAces( file.getId() );
        fileAcl.setAces( aces, fileAcl.isEntriesInheriting() );
      }

      for ( int i = 0; i < fileAcl.getAces().size(); i++ ) {
        RepositoryFileAclAceDto acl = fileAcl.getAces().get( i );
        if ( acl.getRecipient().equals( getSession().getName() ) ) {
          if ( acl.getPermissions().contains( RepositoryFilePermission.ACL_MANAGEMENT.ordinal() )
            || acl.getPermissions().contains( RepositoryFilePermission.ALL.ordinal() ) ) {
            canManage = true;
            break;
          }
        }
      }
    }

    if ( canManage ) {
      Map<String, Serializable> fileMetadata = getRepository().getFileMetadata( file.getId() );
      boolean isHidden = false;

      for ( StringKeyStringValueDto nv : metadata ) {
        // don't add hidden to the list because it is not actually part of the metadata node
        if ( ( nv.getKey().contentEquals( "_PERM_HIDDEN" ) ) ) {
          isHidden = Boolean.parseBoolean( nv.getValue() );
        } else {
          fileMetadata.put( nv.getKey(), nv.getValue() );
        }
      }

      // now update the rest of the metadata
      if ( !file.isFolder() ) {
        getRepository().setFileMetadata( file.getId(), fileMetadata );
      }

      // handle hidden flag if it is different
      if ( file.isHidden() != isHidden ) {
        file.setHidden( isHidden );

          /*
           * Since we cannot simply set the new value, use the RepositoryFileAdapter to create a new instance and then
           * update the original.
           */
        RepositoryFile sourceFile = getRepository().getFileById( file.getId() );
        RepositoryFileDto destFileDto = getRepositoryFileAdapter().toFileDto( sourceFile );

        destFileDto.setHidden( isHidden );

        RepositoryFile destFile = getRepositoryFileAdapter().toFile( destFileDto );

        // add the existing acls and file data
        RepositoryFileAcl acl = getRepository().getAcl( sourceFile.getId() );
        if ( !file.isFolder() ) {
          IRepositoryFileData data = getData( sourceFile );

          getRepository().updateFile( destFile, data, null );
          getRepository().updateAcl( acl );
        } else {
          getRepository().updateFolder( destFile, null );
        }
      }
    } else {
      throw new GeneralSecurityException();
    }
  }