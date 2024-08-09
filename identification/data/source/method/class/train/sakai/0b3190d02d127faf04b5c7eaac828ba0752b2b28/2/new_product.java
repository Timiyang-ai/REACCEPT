public ContextSession getContextSession(String contextId)
	{
        MyLittleSession t = m_contextSessions.get(contextId);
		if (t == null)
		{
			NonPortableSession nPS = new MyNonPortableSession();
			t = new MyLittleSession(MyLittleSession.TYPE_CONTEXT, sessionManager, idManager.createUuid(),this,contextId,
					threadLocalManager,sessionListener,sessionStore,nPS);
			m_contextSessions.put(contextId, t);
			t.setSessionContextId(contextId);
		}

		// mark it as accessed
		t.setAccessed();

		return t;
	}