public static boolean isRequest(HttpServletRequest request) {

		String message_type = request.getParameter(LTI_MESSAGE_TYPE);
		if ( message_type == null ) return false;
		if ( message_type.equals(LTI_MESSAGE_TYPE_BASICLTILAUNCHREQUEST) ||
		     message_type.equals(LTI_MESSAGE_TYPE_TOOLPROXYREGISTRATIONREQUEST) ||
		     message_type.equals(LTI_MESSAGE_TYPE_TOOLPROXY_RE_REGISTRATIONREQUEST) ||
		     message_type.equals(LTI_MESSAGE_TYPE_CONTENTITEMSELECTIONREQUEST) ) {
			// Seems plausible
		} else {
			return false;
		}

		String version = request.getParameter(LTI_VERSION);
		if ( version == null ) return true;
		if ( version.equals(LTI_VERSION_1) || version.equals(LTI_VERSION_2) ) {
			// Another pass
		} else {
			return false;
		}

		return true;
	}