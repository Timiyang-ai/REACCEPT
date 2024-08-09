private void depth(final ComponentEvent<?> event)
	{
		IEventSink sink = event.getSink();

		boolean targetsApplication = sink instanceof Application;
		boolean targetsSession = targetsApplication || sink instanceof Session;
		boolean targetsCycle = targetsSession || sink instanceof RequestCycle;
		boolean targetsComponnet = sink instanceof Component;

		if (!targetsComponnet && !targetsCycle)
		{
			dispatcher.dispatchEvent(sink, event);
			return;
		}

		Component cursor = (targetsCycle) ? source.getPage() : (Component)sink;

		if (cursor instanceof MarkupContainer)
		{
			Visits.visitPostOrder(cursor, new ComponentEventVisitor(event, dispatcher));
		}
		else
		{
			dispatcher.dispatchEvent(cursor, event);
		}
		if (event.isStop())
		{
			return;
		}
		if (targetsCycle)
		{
			dispatcher.dispatchEvent(source.getRequestCycle(), event);
		}
		if (event.isStop())
		{
			return;
		}
		if (targetsSession)
		{
			dispatcher.dispatchEvent(source.getSession(), event);
		}
		if (event.isStop())
		{
			return;
		}
		if (targetsApplication)
		{
			dispatcher.dispatchEvent(source.getApplication(), event);
		}
	}