public void openFolder(String foldername, boolean defaultFolder, boolean write) throws KettleException  {
	    this.write=write;  
    	try {
	    	  if(defaultFolder) {
	          	// get the default folder
	          	this.folder = this.store.getDefaultFolder().getFolder(INBOX_FOLDER);

	  			 if(this.folder==null) throw new KettleException(BaseMessages.getString(PKG, "JobGetMailsFromPOP.InvalidDefaultFolder.Label"));  
	  			 
	  			 if ((folder.getType() & Folder.HOLDS_MESSAGES) == 0) 
	  				 throw new KettleException(BaseMessages.getString(PKG, "MailConnection.DefaultFolderCanNotHoldMessage"));  
	    	  }else {
	    		 // Open specified Folder (for IMAP)
		    	 if(this.protocol==PROTOCOL_IMAP)  {
					this.folder=this.store.getFolder(foldername);
		    	  }	  
		  		 if (this.folder == null || !this.folder.exists()) 
		  			 throw new KettleException(BaseMessages.getString(PKG, "JobGetMailsFromPOP.InvalidFolder.Label"));       
	    	  }
	        if(this.write) {
	        	if(log.isDebug()) log.logDebug(toString(), BaseMessages.getString(PKG, "MailConnection.OpeningFolderInWriteMode.Label", getFolderName()));
	        	this.folder.open(Folder.READ_WRITE);
	        }else {
	        	if(log.isDebug()) log.logDebug(toString(), BaseMessages.getString(PKG, "MailConnection.OpeningFolderInReadMode.Label", getFolderName()));
	        	this.folder.open(Folder.READ_ONLY); 
	        }
	        
	        if(log.isDetailed()) log.logDetailed(toString(), BaseMessages.getString(PKG, "JobGetMailsFromPOP.FolderOpened.Label", getFolderName()));
	        if(log.isDebug()) {
	        	// display some infos on folder
	          	log.logDebug(toString(), BaseMessages.getString(PKG, "JobGetMailsFromPOP.FolderOpened.Name", getFolderName()));
	          	log.logDebug(toString(), BaseMessages.getString(PKG, "JobGetMailsFromPOP.FolderOpened.FullName", this.folder.getFullName()));
	          	log.logDebug(toString(), BaseMessages.getString(PKG, "JobGetMailsFromPOP.FolderOpened.Url", this.folder.getURLName().toString()));
	          	log.logDebug(toString(), BaseMessages.getString(PKG, "JobGetMailsFromPOP.FolderOpened.Subscribed", ""+this.folder.isSubscribed()));
	        }
	        
	    } catch (Exception e) {
	    	throw new KettleException(defaultFolder?BaseMessages.getString(PKG, "JobGetMailsFromPOP.Error.OpeningDefaultFolder"):BaseMessages.getString(PKG, "JobGetMailsFromPOP.Error.OpeningFolder",foldername),e);
	    }
   }