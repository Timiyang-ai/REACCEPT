public void renewSessionId(String oldId, String oldExtendedId, String newId, String newExtendedId)
    {
        try
        {
            Session session = _sessionCache.renewSessionId(oldId, newId, oldExtendedId, newExtendedId); //swap the id over
            if (session == null)
            {
                //session doesn't exist on this context
                return;
            }

            //inform the listeners
            callSessionIdListeners(session, oldId);
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }