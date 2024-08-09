public void get(HttpServletRequest req, HttpServletResponse res, WebContext ctx)
	throws IOException {
		ctx.setVariable(KEY_TITLE, "Settings");
		String operation = req.getParameter(WebConstants.OPERATION);
        String msgType = "success";
        String msg = null;
        if (Util.hasLength(operation) && TOGGLE_AUDIT.equalsIgnoreCase(operation)) {
            if (getFf4j().isEnableAudit()) {
                getFf4j().audit(false);
                msg = "Audit is now DISABLED";
                LOGGER.info("Audit has been disabled");
            } else {
                getFf4j().audit(true);
                msg = "Audit is now ENABLED";
                LOGGER.info("Audit has been enabled");
            }
        }
        ctx.setVariable(KEY_AUDITENABLE, getFf4j().isEnableAudit());
        ctx.setVariable("msgType", msgType);
        ctx.setVariable("msgInfo", msg);
	}