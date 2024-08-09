@Override
    public ActionForward execute(ActionMapping mapping,
            ActionForm formIn,
            HttpServletRequest request,
            HttpServletResponse response) {

        ActionForward forward = super.execute(mapping, formIn, request, response);
        RequestContext rctx = new RequestContext(request);
        User user = rctx.getCurrentUser();

        log.debug("show: " + (request.getAttribute(SHOW_NO_SYSTEMS) == null));
        if (request.getAttribute(SHOW_NO_SYSTEMS) == null) {
            log.debug("adding show commands ..");
            request.setAttribute(SHOW_COMMANDS, Boolean.TRUE);
        }

        List<LabelValueBean> addOnEntitlements = new ArrayList<LabelValueBean>();

        log.debug("Adding virt-entitled droplist entry");
        addOnEntitlements.add(lvl10n(EntitlementManager.VIRTUALIZATION_ENTITLED,
                EntitlementManager.VIRTUALIZATION_ENTITLED));

        log.debug("addonents.size(): " + addOnEntitlements.size());
        if (addOnEntitlements.size() > 0) {
            log.debug("sorting list");
            Collections.sort(addOnEntitlements);
            request.setAttribute(ADDON_ENTITLEMENTS, addOnEntitlements);
            DynaActionForm form = (DynaActionForm)formIn;
            form.set(ADDON_ENTITLEMENT, addOnEntitlements.get(0).getValue());
        }
        setupCounts(request, user);

        return forward;
    }