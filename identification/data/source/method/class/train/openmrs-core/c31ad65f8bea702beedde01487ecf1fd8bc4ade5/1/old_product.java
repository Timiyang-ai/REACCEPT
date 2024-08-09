protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object obj,
	        BindException errors) throws Exception {
		
		HttpSession httpSession = request.getSession();
		
		String view = getFormView();
		String action = request.getParameter("action");
		
		if (Context.isAuthenticated()) {
			Field field = (Field) obj;
			field = setObjects(field, request);
			
			if (action.equals(Context.getMessageSourceService().getMessage("general.delete"))) {
				try {
					Context.getFormService().purgeField(field);
				} catch (DataIntegrityViolationException e) {
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "error.object.inuse.cannot.purge");
					return new ModelAndView(new RedirectView("field.form?fieldId=" + field.getFieldId()));
				}
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Field.Deleted");
			} else {
				Context.getFormService().saveField(field);
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Field.saved");
			}
		}
			
		view = getSuccessView();
		view = view + "?phrase=" + request.getParameter("phrase");
		
		return new ModelAndView(new RedirectView(view));
	}