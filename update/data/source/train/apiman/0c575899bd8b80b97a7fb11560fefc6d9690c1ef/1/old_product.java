public static XContentBuilder marshall(ServiceVersionBean bean) throws StorageException {
        try {
            ServiceBean service = bean.getService();
            OrganizationBean org = service.getOrganization();
            preMarshall(bean);
            XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                    .field("organizationId", org.getId())
                    .field("organizationName", org.getName())
                    .field("serviceId", service.getId())
                    .field("serviceName", service.getName())
                    .field("serviceDescription", service.getDescription())
                    .field("version", bean.getVersion())
                    .field("status", bean.getStatus())
                    .field("createdBy", bean.getCreatedBy())
                    .field("createdOn", bean.getCreatedOn().getTime())
                    .field("modifiedBy", bean.getModifiedBy())
                    .field("modifiedOn", bean.getModifiedOn().getTime())
                    .field("publishedOn", bean.getPublishedOn() != null ? bean.getPublishedOn().getTime() : null)
                    .field("retiredOn", bean.getRetiredOn() != null ? bean.getRetiredOn().getTime() : null)
                    .field("publicService", bean.isPublicService())
                    .field("endpoint", bean.getEndpoint())
                    .field("endpointType", bean.getEndpointType())
                    .field("definitionType", bean.getDefinitionType());
            Set<ServiceGatewayBean> gateways = bean.getGateways();
            if (gateways != null) {
                builder.startArray("gateways");
                for (ServiceGatewayBean gateway : gateways) {
                    builder.startObject()
                        .field("gatewayId", gateway.getGatewayId())
                    .endObject();
                }
                builder.endArray();
            }
            Set<ServicePlanBean> plans = bean.getPlans();
            if (plans != null) {
                builder.startArray("plans");
                for (ServicePlanBean plan : plans) {
                    builder.startObject()
                        .field("planId", plan.getPlanId())
                        .field("version", plan.getVersion())
                    .endObject();
                }
                builder.endArray();
            }
            Map<String, String> endpointProperties = bean.getEndpointProperties();
            if (endpointProperties != null) {
                builder.startObject("endpointProperties");
                for (Entry<String, String> property : endpointProperties.entrySet()) {
                    builder.field(property.getKey(), property.getValue());
                }
                builder.endObject();
            }
            builder.endObject();
            postMarshall(bean);
            return builder;
        } catch (IOException e) {
            throw new StorageException(e);
        }
    }