public static boolean isRequest(HttpServletRequest request) {

		String message_type = request.getParameter(LTI_MESSAGE_TYPE);
		if ( message_type == null ) return false;
		if ( message_type.equals(LTI_MESSAGE_TYPE_BASICLTILAUNCHREQUEST) ||
		     message_type.equals(LTI_MESSAGE_TYPE_CONTENTITEMSELECTIONREQUEST) ) {
			// Seems plausible
		} else {
			return false;
		}

		String version = request.getParameter(LTI_VERSION);
		if ( version == null ) return true;
		if ( !version.equals(LTI_VERSION_1) ) return false;

		return true;
	}