public ToolSession getToolSession(String placementId)
	{
		MyLittleSession t = m_toolSessions.get(placementId);
		if (t == null)
		{
			NonPortableSession nPS = new MyNonPortableSession();
			t = new MyLittleSession(MyLittleSession.TYPE_TOOL, sessionManager,idManager.createUuid(),this,placementId,
					threadLocalManager, sessionListener,sessionStore,nPS);
			m_toolSessions.put(placementId, t);
			// try to populate the tool id and context when the session is created
			if (sessionStore instanceof SessionComponent) {
				String sakaiToolId = ((SessionComponent)sessionStore).identifyCurrentTool();
				t.setSessionToolId(sakaiToolId);
				String context = ((SessionComponent)sessionStore).identifyCurrentContext();
				t.setSessionContextId(context);
			}
		}

		// mark it as accessed
		t.setAccessed();

		return t;
	}