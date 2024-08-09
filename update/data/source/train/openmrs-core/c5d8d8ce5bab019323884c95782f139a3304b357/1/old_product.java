protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object obj,
	                                BindException errors) throws Exception {
		
		HttpSession httpSession = request.getSession();
		
		String view = getFormView();
		if (Context.isAuthenticated()) {
			try {
				Location location = (Location) obj;
				LocationService locationService = Context.getLocationService();
				
				//if the user was editing the location
				if (request.getParameter("saveLocation") != null) {
					locationService.saveLocation(location);
					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Location.saved");
				}
				//the 'retire this location' button was clicked
				else if (request.getParameter("retireLocation") != null) {
					//make sure a reason was filled in for retiring the location
					// this isn't done in the validator because we only want it checked if the user
					// clicked the "retired location" button
					if (!StringUtils.hasLength(location.getRetireReason())) {
						errors.reject("retireReason", "general.retiredReason.empty");
						return showForm(request, response, errors);
					}
					locationService.retireLocation(location, location.getRetireReason());
					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Location.retired");
				}
				//the 'unretire this location' button was clicked
				else if (request.getParameter("unretireLocation") != null) {
					locationService.unretireLocation(location);
					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Location.unretired");
				}
			}
			catch (APIException e) {
				log.error("Error while saving location: " + obj, e);
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, e.getMessage());
				return showForm(request, response, errors);
			}
			
			view = getSuccessView();
		}
		
		return new ModelAndView(new RedirectView(view));
	}