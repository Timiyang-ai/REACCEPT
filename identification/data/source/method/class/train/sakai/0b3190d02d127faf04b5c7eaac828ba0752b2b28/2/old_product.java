public ContextSession getContextSession(String contextId)
	{
		ContextSession t = (ContextSession) m_contextSessions.get(contextId);
		if (t == null)
		{
			NonPortableSession nPS = new MyNonPortableSession();
			t = new MyLittleSession(sessionManager, idManager.createUuid(),this,contextId,
					threadLocalManager,sessionListener,sessionStore,nPS);
			m_contextSessions.put(contextId, t);
		}

		// mark it as accessed
		((MyLittleSession) t).setAccessed();

		return t;
	}