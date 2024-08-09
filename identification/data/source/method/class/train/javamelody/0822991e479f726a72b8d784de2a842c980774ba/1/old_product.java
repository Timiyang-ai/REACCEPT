@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (!httpAuth.isAllowed(req, resp)) {
			return;
		}

		final long start = System.currentTimeMillis();
		final CollectorController collectorController = new CollectorController(collectorServer);
		final String application = collectorController.getApplication(req, resp);
		I18N.bindLocale(req.getLocale());
		try {
			if (application == null) {
				CollectorController.writeOnlyAddApplication(resp);
				return;
			}
			if (!collectorServer.isApplicationDataAvailable(application)
					&& req.getParameter(ACTION_PARAMETER) == null) {
				CollectorController.writeDataUnavailableForApplication(application, resp);
				return;
			}
			collectorController.doMonitoring(req, resp, application);
		} finally {
			I18N.unbindLocale();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("monitoring from " + req.getRemoteAddr() + ", request="
						+ req.getRequestURI()
						+ (req.getQueryString() != null ? '?' + req.getQueryString() : "")
						+ ", application=" + application + " in "
						+ (System.currentTimeMillis() - start) + "ms");
			}
		}
	}