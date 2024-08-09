@GET
	@Path("/metadata")
	public Response conformance() throws IOException {
		Builder request = getRequest(RequestTypeEnum.OPTIONS, RestOperationTypeEnum.METADATA);
		IRestfulResponse response = request.build().getResponse();
		response.addHeader(Constants.HEADER_CORS_ALLOW_ORIGIN, "*");
		
		IBaseResource conformance;
		FhirVersionEnum fhirContextVersion = super.getFhirContext().getVersion().getVersion();
		switch (fhirContextVersion) {
			case R4:
				conformance = myR4CapabilityStatement;
				break;
			case DSTU3:
				conformance = myDstu3CapabilityStatement;
				break;
			case DSTU2_1:
				conformance = myDstu2_1Conformance;
				break;
			case DSTU2_HL7ORG:
				conformance = myDstu2Hl7OrgConformance;
				break;
			case DSTU2:
				conformance = myDstu2Conformance;
				break;
			default:
				throw new ConfigurationException("Unsupported Fhir version: " + fhirContextVersion);
		}
		
		if (conformance != null) {
			Set<SummaryEnum> summaryMode = Collections.emptySet();
			return (Response) response.streamResponseAsResource(conformance, false, summaryMode, Constants.STATUS_HTTP_200_OK, null, true, false);
		}
		return (Response) response.returnResponse(null, Constants.STATUS_HTTP_500_INTERNAL_ERROR, true, null, getResourceType().getSimpleName());
	}