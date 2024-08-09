public int doStartTag() {
		
		errorOccurred = false;
		HttpServletResponse httpResponse = (HttpServletResponse) pageContext.getResponse();
		HttpSession httpSession = pageContext.getSession();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String request_ip_addr = request.getLocalAddr();
		String session_ip_addr = (String) httpSession.getAttribute(WebConstants.OPENMRS_CLIENT_IP_HTTPSESSION_ATTR);
		
		UserContext userContext = Context.getUserContext();
		
		if (userContext == null && privilege != null) {
			log.error("userContext is null. Did this pass through a filter?");
			//httpSession.removeAttribute(WebConstants.OPENMRS_CONTEXT_HTTPSESSION_ATTR);
			//TODO find correct error to throw 
			throw new APIException("The context is currently null.  Please try reloading the site.");
		}
		
		// Parse comma-separated list of privileges in allPrivileges and anyPrivileges attributes
		String[] allPrivilegesArray = StringUtils.commaDelimitedListToStringArray(allPrivileges);
		String[] anyPrivilegeArray = StringUtils.commaDelimitedListToStringArray(anyPrivilege);
		
		boolean hasPrivilege = hasPrivileges(userContext, privilege, allPrivilegesArray, anyPrivilegeArray);
		if (!hasPrivilege) {
			errorOccurred = true;
			if (userContext.isAuthenticated()) {
				String referer = request.getHeader("Referer");
				// If the user has just authenticated, but is still not authorized to see the page.
				if (referer != null && referer.contains("login.")) {
					try {
						httpResponse.sendRedirect(request.getContextPath()); // Redirect to the home page.
						return SKIP_PAGE;
					}
					catch (IOException e) {
						// oops, cannot redirect
						log.error("Unable to redirect to the home page", e);
						throw new APIException(e);
					}
				}
				
				String errorCodeOrMsg = "";
				if (missingPrivilegesBuffer != null) {
					String requiredPrivileges = missingPrivilegesBuffer.toString();
					MessageSourceService mss = Context.getMessageSourceService();
					errorCodeOrMsg = mss.getMessage("general.authentication.unableToViewPage",
					    new Object[] { requiredPrivileges }, null);
					errorCodeOrMsg += "<br />" + mss.getMessage("general.authentication.accountHasNoPrivilege");
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, errorCodeOrMsg);
					httpSession.setAttribute(WebConstants.FOUND_MISSING_PRIVILEGES, true);
					httpSession.setAttribute(WebConstants.REQUIRED_PRIVILEGES, requiredPrivileges);
					
					if (StringUtils.hasText(redirect)) {
						httpSession.setAttribute(WebConstants.DENIED_PAGE, redirect);
					} else if (StringUtils.hasText(referer)) {
						//This is not exactly correct all the time
						httpSession.setAttribute(WebConstants.DENIED_PAGE, referer);
					}
				} else {
					//Why would there be no missing privileges yet the hasPrivileges(String priv) returned false
					errorCodeOrMsg = "require.unauthorized";
				}
				
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, errorCodeOrMsg);
				log.warn("The user: '" + Context.getAuthenticatedUser() + "' has attempted to access: " + redirect
				        + " which requires privilege: " + privilege + " or one of: " + allPrivileges + " or any of "
				        + anyPrivilege);
			} else
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "require.login");
		} else if (hasPrivilege && userContext.isAuthenticated()) {
			// redirect users to password change form
			User user = userContext.getAuthenticatedUser();
			log.debug("Login redirect: " + redirect);
			if (new UserProperties(user.getUserProperties()).isSupposedToChangePassword()
			        && !redirect.contains("options.form")) {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "User.password.change");
				errorOccurred = true;
				redirect = request.getContextPath() + "/options.form#Change Login Info";
				otherwise = redirect;
				try {
					httpResponse.sendRedirect(redirect);
					return SKIP_PAGE;
				}
				catch (IOException e) {
					// oops, cannot redirect
					log.error("Unable to redirect for password change: " + redirect, e);
					throw new APIException(e);
				}
			}
		}
		
		if (differentIpAddresses(session_ip_addr, request_ip_addr)) {
			errorOccurred = true;
			// stops warning message in IE when refreshing repeatedly
			if ("0.0.0.0".equals(request_ip_addr) == false) {
				log.warn("Invalid ip addr: expected " + session_ip_addr + ", but found: " + request_ip_addr);
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "require.ip_addr");
			}
		}
		
		log.debug("session ip addr: " + session_ip_addr);
		
		if (errorOccurred) {
			String url = "";
			if (redirect != null && !redirect.equals(""))
				url = request.getContextPath() + redirect;
			else
				url = request.getRequestURI();
			
			if (request.getQueryString() != null)
				url = url + "?" + request.getQueryString();
			httpSession.setAttribute(WebConstants.OPENMRS_LOGIN_REDIRECT_HTTPSESSION_ATTR, url);
			try {
				httpResponse.sendRedirect(request.getContextPath() + otherwise);
				return SKIP_PAGE;
			}
			catch (IOException e) {
				// oops, cannot redirect
				throw new APIException(e);
			}
		}
		
		return SKIP_BODY;
	}