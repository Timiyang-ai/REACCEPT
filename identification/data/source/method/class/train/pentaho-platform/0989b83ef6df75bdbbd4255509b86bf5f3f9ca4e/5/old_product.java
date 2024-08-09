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
      boolean isHidden = RepositoryFile.HIDDEN_BY_DEFAULT;
      boolean isSchedulable = RepositoryFile.SCHEDULABLE_BY_DEFAULT;

      fileMetadata.remove( RepositoryFile.HIDDEN_KEY );
      for ( StringKeyStringValueDto nv : metadata ) {
        // don't add hidden to the list because it is not actually part of the metadata node
        String key = nv.getKey();
        if ( RepositoryFile.HIDDEN_KEY.equalsIgnoreCase( key ) ) {
          isHidden = BooleanUtils.toBoolean( nv.getValue() );
          continue;
        }
        if ( RepositoryFile.SCHEDULABLE_KEY.equalsIgnoreCase( key ) ) {
          isSchedulable = BooleanUtils.toBoolean( nv.getValue() );
        }
        fileMetadata.put( key, nv.getValue() );
      }

      // now update the rest of the metadata
      if ( !file.isFolder() ) {
        getRepository().setFileMetadata( file.getId(), fileMetadata );
      }

      // handle hidden flag if it is different
      if ( file.isHidden() != isHidden ) {
        file.setHidden( isHidden );
        file.setSchedulable( isSchedulable );

          /*
           * Since we cannot simply set the new value, use the RepositoryFileAdapter to create a new instance and then
           * update the original.
           */
        RepositoryFile sourceFile = getRepository().getFileById( file.getId() );
        RepositoryFileDto destFileDto = toFileDto( sourceFile, null, false );

        destFileDto.setHidden( isHidden );
        destFileDto.setSchedulable( isSchedulable );

        RepositoryFile destFile = toFile( destFileDto );

        // add the existing acls and file data
        RepositoryFileAcl acl = getRepository().getAcl( sourceFile.getId() );
        if ( !file.isFolder() ) {
          IRepositoryFileData data = RepositoryFileHelper.getFileData( sourceFile );

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