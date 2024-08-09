private void publishApi(ActionBean action) throws ActionException {
        if (!securityContext.hasPermission(PermissionType.apiAdmin, action.getOrganizationId()))
            throw ExceptionFactory.notAuthorizedException();

        ApiVersionBean versionBean = null;
        try {
            versionBean = orgs.getApiVersion(action.getOrganizationId(), action.getEntityId(), action.getEntityVersion());
        } catch (ApiVersionNotFoundException e) {
            throw ExceptionFactory.actionException(Messages.i18n.format("ApiNotFound")); //$NON-NLS-1$
        }

        // Validate that it's ok to perform this action - API must be Ready.
        if (!versionBean.isPublicAPI() && versionBean.getStatus() != ApiStatus.Ready) {
            throw ExceptionFactory.actionException(Messages.i18n.format("InvalidApiStatus")); //$NON-NLS-1$
        }
        if (versionBean.isPublicAPI()) {
            if (versionBean.getStatus() == ApiStatus.Retired || versionBean.getStatus() == ApiStatus.Created) {
                throw ExceptionFactory.actionException(Messages.i18n.format("InvalidApiStatus")); //$NON-NLS-1$
            }
            if (versionBean.getStatus() == ApiStatus.Published) {
                Date modOn = versionBean.getModifiedOn();
                Date publishedOn = versionBean.getPublishedOn();
                int c = modOn.compareTo(publishedOn);
                if (c <= 0) {
                    throw ExceptionFactory.actionException(Messages.i18n.format("ApiRePublishNotRequired")); //$NON-NLS-1$
                }
            }
        }

        Api gatewayApi = new Api();
        gatewayApi.setEndpoint(versionBean.getEndpoint());
        gatewayApi.setEndpointType(versionBean.getEndpointType().toString());
        if (versionBean.getEndpointContentType() != null) {
            gatewayApi.setEndpointContentType(versionBean.getEndpointContentType().toString());
        }
        gatewayApi.setEndpointProperties(versionBean.getEndpointProperties());
        gatewayApi.setOrganizationId(versionBean.getApi().getOrganization().getId());
        gatewayApi.setApiId(versionBean.getApi().getId());
        gatewayApi.setVersion(versionBean.getVersion());
        gatewayApi.setPublicAPI(versionBean.isPublicAPI());
        boolean hasTx = false;
        try {
            if (versionBean.isPublicAPI()) {
                List<Policy> policiesToPublish = new ArrayList<>();
                List<PolicySummaryBean> apiPolicies = query.getPolicies(action.getOrganizationId(),
                        action.getEntityId(), action.getEntityVersion(), PolicyType.Api);
                storage.beginTx();
                hasTx = true;
                for (PolicySummaryBean policySummaryBean : apiPolicies) {
                    PolicyBean apiPolicy = storage.getPolicy(PolicyType.Api, action.getOrganizationId(),
                            action.getEntityId(), action.getEntityVersion(), policySummaryBean.getId());
                    Policy policyToPublish = new Policy();
                    policyToPublish.setPolicyJsonConfig(apiPolicy.getConfiguration());
                    policyToPublish.setPolicyImpl(apiPolicy.getDefinition().getPolicyImpl());
                    policiesToPublish.add(policyToPublish);
                }
                gatewayApi.setApiPolicies(policiesToPublish);
            }
        } catch (StorageException e) {
            throw ExceptionFactory.actionException(Messages.i18n.format("PublishError"), e); //$NON-NLS-1$
        } finally {
            if (hasTx) {
                storage.rollbackTx();
            }
        }

        // Publish the API to all relevant gateways
        try {
            storage.beginTx();
            Set<ApiGatewayBean> gateways = versionBean.getGateways();
            if (gateways == null) {
                throw new PublishingException("No gateways specified for API!"); //$NON-NLS-1$
            }
            for (ApiGatewayBean apiGatewayBean : gateways) {
                IGatewayLink gatewayLink = createGatewayLink(apiGatewayBean.getGatewayId());
                gatewayLink.publishApi(gatewayApi);
                gatewayLink.close();
            }

            versionBean.setStatus(ApiStatus.Published);
            versionBean.setPublishedOn(new Date());

            ApiBean api = storage.getApi(action.getOrganizationId(), action.getEntityId());
            if (api == null) {
                throw new PublishingException("Error: could not find API - " + action.getOrganizationId() + "=>" + action.getEntityId()); //$NON-NLS-1$ //$NON-NLS-2$
            }
            if (api.getNumPublished() == null) {
                api.setNumPublished(1);
            } else {
                api.setNumPublished(api.getNumPublished() + 1);
            }

            storage.updateApi(api);
            storage.updateApiVersion(versionBean);
            storage.createAuditEntry(AuditUtils.apiPublished(versionBean, securityContext));
            storage.commitTx();
        } catch (PublishingException e) {
            storage.rollbackTx();
            throw ExceptionFactory.actionException(Messages.i18n.format("PublishError"), e); //$NON-NLS-1$
        } catch (Exception e) {
            storage.rollbackTx();
            throw ExceptionFactory.actionException(Messages.i18n.format("PublishError"), e); //$NON-NLS-1$
        }

        log.debug(String.format("Successfully published API %s on specified gateways: %s", //$NON-NLS-1$
                versionBean.getApi().getName(), versionBean.getApi()));
    }