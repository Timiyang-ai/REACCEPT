@Override
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object obj,
	        BindException errors) throws Exception {
		
		HttpSession httpSession = request.getSession();
		ConceptService cs = Context.getConceptService();
		
		if (Context.isAuthenticated()) {
			
			ConceptFormBackingObject conceptBackingObject = (ConceptFormBackingObject) obj;
			MessageSourceAccessor msa = getMessageSourceAccessor();
			String action = request.getParameter("action");
			
			if (action.equals(msa.getMessage("general.retire"))) {
				Concept concept = conceptBackingObject.getConcept();
				try {
					String reason = request.getParameter("retiredReason");
					if (!StringUtils.hasText(reason)) {
						reason = msa.getMessage("general.default.retireReason");
					}
					cs.retireConcept(concept, reason);
					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Concept.concept.retired.successFully");
					return new ModelAndView(new RedirectView(getSuccessView() + "?conceptId=" + concept.getConceptId()));
				}
				catch (APIException e) {
					log.error("Unable to Retire concept because an error occurred: " + concept, e);
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "general.cannot.retire");
				}
				// return to the edit screen because an error was thrown
				return new ModelAndView(new RedirectView(getSuccessView() + "?conceptId=" + concept.getConceptId()));
				
			} else if (action.equals(msa.getMessage("general.unretire"))) {
				Concept concept = conceptBackingObject.getConcept();
				try {
					concept.setRetired(false);
					cs.saveConcept(concept);
					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Concept.concept.unRetired.successFully");
					return new ModelAndView(new RedirectView(getSuccessView() + "?conceptId=" + concept.getConceptId()));
				}
				catch (ConceptsLockedException cle) {
					log.error("Tried to unretire concept while concepts were locked", cle);
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Concept.concepts.locked.unRetire");
				}
				catch (DuplicateConceptNameException e) {
					log.error("Tried to unretire concept with a duplicate name", e);
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "general.cannot.unretire");
				}
				catch (APIException e) {
					log.error("Error while trying to unretire concept", e);
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "general.cannot.unretire");
				}
				// return to the edit screen because an error was thrown
				return new ModelAndView(new RedirectView(getSuccessView() + "?conceptId=" + concept.getConceptId()));
				
			} else if (action.equals(msa.getMessage("Concept.delete", "Delete Concept"))) {
				Concept concept = conceptBackingObject.getConcept();
				try {
					cs.purgeConcept(concept);
					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Concept.deleted");
					return new ModelAndView(new RedirectView("index.htm"));
				}
				catch (ConceptsLockedException cle) {
					log.error("Tried to delete concept while concepts were locked", cle);
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Concept.concepts.locked");
				}
				catch (DataIntegrityViolationException e) {
					log.error("Unable to delete a concept because it is in use: " + concept, e);
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Concept.cannot.delete");
				}
				catch (Exception e) {
					log.error("Unable to delete concept because an error occurred: " + concept, e);
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Concept.cannot.delete");
				}
				// return to the edit screen because an error was thrown
				return new ModelAndView(new RedirectView(getSuccessView() + "?conceptId=" + concept.getConceptId()));
			} else {
				Concept concept = conceptBackingObject.getConceptFromFormData();
				//if the user is editing a concept, initialise the associated creator property
				//this is aimed at avoiding a lazy initialisation exception when rendering
				//the jsp after validation has failed
				if (concept.getConceptId() != null) {
					concept.getCreator().getPersonName();
				}
				
				try {
					ValidateUtil.validate(concept, errors);
					
					validateConceptUsesPersistedObjects(concept, errors);
					
					if (!errors.hasErrors()) {
						if (action.equals(msa.getMessage("Concept.cancel"))) {
							return new ModelAndView(new RedirectView("index.htm"));
						}
						cs.saveConcept(concept);
						httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Concept.saved");
						if (action.equals(msa.getMessage("Concept.save"))) {
							return new ModelAndView(new RedirectView("concept.htm" + "?conceptId=" + concept.getConceptId()));
						}
						return new ModelAndView(new RedirectView(getSuccessView() + "?conceptId=" + concept.getConceptId()));
					}
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Concept.cannot.save");
				}
				catch (ConceptsLockedException cle) {
					log.error("Tried to save concept while concepts were locked", cle);
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Concept.concepts.locked");
					errors.reject("concept", "Concept.concepts.locked");
				}
				catch (DuplicateConceptNameException e) {
					log.error("Tried to save concept with a duplicate name", e);
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Concept.cannot.save");
					errors.rejectValue("concept", "Concept.name.duplicate");
				}
				catch (APIException e) {
					log.error("Error while trying to save concept", e);
					httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "Concept.cannot.save");
					errors.reject("concept", "Concept.cannot.save");
				}
			}
			// return to the edit form because an error was thrown
			return showForm(request, response, errors);
		}
		
		return new ModelAndView(new RedirectView(getFormView()));
	}