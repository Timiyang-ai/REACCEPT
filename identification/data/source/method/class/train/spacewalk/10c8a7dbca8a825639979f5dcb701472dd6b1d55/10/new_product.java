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

        List addOnEntitlements = new ArrayList();
        if (log.isDebugEnabled()) {
            log.debug("user.getOrg().getEnts: " + user.getOrg().getEntitlements());
        }

        // Virt is an addon to update so just check to make sure we
        // have virt.  This is different from provisioning and monitoring
        if (user.getOrg().hasEntitlement(OrgFactory.getEntitlementVirtualization())) {
            log.debug("Adding virt-entitled droplist entry");
            addOnEntitlements.add(lvl10n(EntitlementManager.VIRTUALIZATION_ENTITLED,
                    EntitlementManager.VIRTUALIZATION_ENTITLED));
            request.setAttribute(SHOW_ADDON_ASPECTS, Boolean.TRUE);
        }
        if (user.getOrg().hasEntitlement(
                OrgFactory.getEntitlementVirtualizationPlatform())) {
            log.debug("Adding virt-host-entitled droplist entry");
            addOnEntitlements.add(lvl10n(
                    EntitlementManager.VIRTUALIZATION_PLATFORM_ENTITLED,
                    EntitlementManager.VIRTUALIZATION_PLATFORM_ENTITLED));
            request.setAttribute(SHOW_ADDON_ASPECTS, Boolean.TRUE);
        }

        if (user.getOrg().hasEntitlement(OrgFactory.getEntitlementEnterprise())) {
            setIfSlotsAvailable(SHOW_MANAGEMENT_ASPECTS,
                    request, user,
                    EntitlementManager.MANAGEMENT);



            if (user.getOrg().hasEntitlement(OrgFactory.getEntitlementMonitoring()) &&
                    hasMonitoringAcl(user, request)) {
                addOnEntitlements.add(lvl10n("monitoring_entitled",
                        EntitlementManager.MONITORING_ENTITLED));
                request.setAttribute(SHOW_MONITORING, Boolean.TRUE);
                request.setAttribute(SHOW_ADDON_ASPECTS, Boolean.TRUE);
            }

            if (user.getOrg().hasEntitlement(OrgFactory.getEntitlementProvisioning())) {
                addOnEntitlements.add(lvl10n("provisioning_entitled",
                        EntitlementManager.PROVISIONING_ENTITLED));
                request.setAttribute(SHOW_ADDON_ASPECTS, Boolean.TRUE);
            }
        }

        log.debug("addonents.size(): " + addOnEntitlements.size());
        if (addOnEntitlements.size() > 0) {
            log.debug("sorting list");
            Collections.sort(addOnEntitlements);
            request.setAttribute(ADDON_ENTITLEMENTS, addOnEntitlements);
            DynaActionForm form = (DynaActionForm)formIn;
            form.set(ADDON_ENTITLEMENT,
                    ((LabelValueBean) addOnEntitlements.get(0)).getValue());
        }
        setupCounts(request, user);

        setIfSlotsAvailable(SHOW_UPDATE_ASPECTS,
                request, user,
                EntitlementManager.UPDATE);


        setupCounts(request, user);



        return forward;
    }