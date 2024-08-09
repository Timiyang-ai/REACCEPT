protected void renewSessionId (Session session, String newId, String newExtendedId)
    throws Exception
    {
        if (session == null)
            return;
        
        try (Lock lock = session.lock())
        {
            String oldId = session.getId();
            session.checkValidForWrite(); //can't change id on invalid session
            session.getSessionData().setId(newId);
            session.getSessionData().setLastSaved(0); //pretend that the session has never been saved before to get a full save
            session.getSessionData().setDirty(true);  //ensure we will try to write the session out    
            session.setExtendedId(newExtendedId); //remember the new extended id
            session.setIdChanged(true); //session id changed
            
            doPutIfAbsent(newId, session); //put the new id into our map
            doDelete (oldId); //take old out of map
            
            if (_sessionDataStore != null)
            {
                _sessionDataStore.delete(oldId);  //delete the session data with the old id
                _sessionDataStore.store(newId, session.getSessionData()); //save the session data with the new id
            }
            if (LOG.isDebugEnabled())
                LOG.debug ("Session id {} swapped for new id {}", oldId, newId);
        }
    }