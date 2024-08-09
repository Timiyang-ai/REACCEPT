public void renewSessionId(String oldId, String oldExtendedId, String newId, String newExtendedId)
    {
        Session session = null;
        try
        {
            //the use count for the session will be incremented in renewSessionId
            session = _sessionCache.renewSessionId(oldId, newId, oldExtendedId, newExtendedId); //swap the id over
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
        finally
        {
            if (session != null)
            {
                try
                {
                    _sessionCache.release(newId, session);
                }
                catch (Exception e)
                {
                    LOG.warn(e);
                }
            }
        }
    }