public ToolSession getToolSession(String placementId)
	{
		ToolSession t = (ToolSession) m_toolSessions.get(placementId);
		if (t == null)
		{
			NonPortableSession nPS = new MyNonPortableSession();
			t = new MyLittleSession(sessionManager,idManager.createUuid(),this,placementId,
					threadLocalManager, sessionListener,sessionStore,nPS);
			m_toolSessions.put(placementId, t);
		}

		// mark it as accessed
		((MyLittleSession) t).setAccessed();

		return t;
	}