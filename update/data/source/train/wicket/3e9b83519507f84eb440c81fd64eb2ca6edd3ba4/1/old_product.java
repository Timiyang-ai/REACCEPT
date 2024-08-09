protected final CharSequence renderAjaxAttributes(final Component component,
		AjaxRequestAttributes attributes)
	{
		JSONObject attributesJson = new JSONObject();

		try
		{
			attributesJson.put(AjaxRequestAttributes.J_URL, getCallbackUrl());
			Method method = attributes.getMethod();
			if (Method.POST == method)
			{
				attributesJson.put(AjaxRequestAttributes.J_METHOD, method);
			}

			if (component instanceof Page == false)
			{
				String componentId = component.getMarkupId();
				attributesJson.put(AjaxRequestAttributes.J_MARKUP_ID, componentId);
			}

			String formId = attributes.getFormId();
			if (Strings.isEmpty(formId) == false)
			{
				attributesJson.put(AjaxRequestAttributes.J_FORM_ID, formId);
			}

			if (attributes.isMultipart())
			{
				attributesJson.put(AjaxRequestAttributes.J_IS_MULTIPART, true);
			}

			String submittingComponentId = attributes.getSubmittingComponentName();
			if (Strings.isEmpty(submittingComponentId) == false)
			{
				attributesJson.put(AjaxRequestAttributes.J_SUBMITTING_COMPONENT_NAME,
					submittingComponentId);
			}

			String indicatorId = findIndicatorId();
			if (Strings.isEmpty(indicatorId) == false)
			{
				attributesJson.put(AjaxRequestAttributes.J_INDICATOR_ID, indicatorId);
			}

			for (IAjaxCallListener ajaxCallListener : attributes.getAjaxCallListeners())
			{
				if (ajaxCallListener != null)
				{
					CharSequence beforeHandler = ajaxCallListener.getBeforeHandler(component);
					appendListenerHandler(beforeHandler, attributesJson,
						AjaxRequestAttributes.J_BEFORE_HANDLER, BEFORE_HANDLER_FUNCTION_TEMPLATE);

					CharSequence beforeSendHandler = ajaxCallListener.getBeforeSendHandler(component);
					appendListenerHandler(beforeSendHandler, attributesJson,
						AjaxRequestAttributes.J_BEFORE_SEND_HANDLER,
						BEFORE_SEND_HANDLER_FUNCTION_TEMPLATE);

					CharSequence afterHandler = ajaxCallListener.getAfterHandler(component);
					appendListenerHandler(afterHandler, attributesJson,
						AjaxRequestAttributes.J_AFTER_HANDLER, AFTER_HANDLER_FUNCTION_TEMPLATE);

					CharSequence successHandler = ajaxCallListener.getSuccessHandler(component);
					appendListenerHandler(successHandler, attributesJson,
						AjaxRequestAttributes.J_SUCCESS_HANDLER, SUCCESS_HANDLER_FUNCTION_TEMPLATE);

					CharSequence failureHandler = ajaxCallListener.getFailureHandler(component);
					appendListenerHandler(failureHandler, attributesJson,
						AjaxRequestAttributes.J_FAILURE_HANDLER, FAILURE_HANDLER_FUNCTION_TEMPLATE);

					CharSequence completeHandler = ajaxCallListener.getCompleteHandler(component);
					appendListenerHandler(completeHandler, attributesJson,
						AjaxRequestAttributes.J_COMPLETE_HANDLER,
						COMPLETE_HANDLER_FUNCTION_TEMPLATE);

					CharSequence precondition = ajaxCallListener.getPrecondition(component);
					appendListenerHandler(precondition, attributesJson,
						AjaxRequestAttributes.J_PRECONDITION, PRECONDITION_FUNCTION_TEMPLATE);
				}
			}

			JSONArray extraParameters = JsonUtils.asArray(attributes.getExtraParameters());

			if (extraParameters.length() > 0)
			{
				attributesJson.put(AjaxRequestAttributes.J_EXTRA_PARAMETERS, extraParameters);
			}

			List<CharSequence> dynamicExtraParameters = attributes.getDynamicExtraParameters();
			if (dynamicExtraParameters != null)
			{
				for (CharSequence dynamicExtraParameter : dynamicExtraParameters)
				{
					String func = String.format(DYNAMIC_PARAMETER_FUNCTION_TEMPLATE,
						dynamicExtraParameter);
					JsonFunction function = new JsonFunction(func);
					attributesJson.append(AjaxRequestAttributes.J_DYNAMIC_PARAMETER_FUNCTION,
						function);
				}
			}

			if (attributes.isAsynchronous() == false)
			{
				attributesJson.put(AjaxRequestAttributes.J_IS_ASYNC, false);
			}

			String[] eventNames = attributes.getEventNames();
			if (eventNames.length == 1)
			{
				attributesJson.put(AjaxRequestAttributes.J_EVENT_NAME, eventNames[0]);
			}
			else
			{
				for (String eventName : eventNames)
				{
					attributesJson.append(AjaxRequestAttributes.J_EVENT_NAME, eventName);
				}
			}

			AjaxChannel channel = attributes.getChannel();
			if (channel != null)
			{
				attributesJson.put(AjaxRequestAttributes.J_CHANNEL, channel);
			}

			if (attributes.isAllowDefault())
			{
				attributesJson.put(AjaxRequestAttributes.J_IS_ALLOW_DEFAULT, true);
			}

			Duration requestTimeout = attributes.getRequestTimeout();
			if (requestTimeout != null)
			{
				attributesJson.put(AjaxRequestAttributes.J_REQUEST_TIMEOUT,
					requestTimeout.getMilliseconds());
			}

			boolean wicketAjaxResponse = attributes.isWicketAjaxResponse();
			if (wicketAjaxResponse == false)
			{
				attributesJson.put(AjaxRequestAttributes.J_IS_WICKET_AJAX_RESPONSE, false);
			}

			String dataType = attributes.getDataType();
			if (AjaxRequestAttributes.XML_DATA_TYPE.equals(dataType) == false)
			{
				attributesJson.put(AjaxRequestAttributes.J_DATATYPE, dataType);
			}

			ThrottlingSettings throttlingSettings = attributes.getThrottlingSettings();
			if (throttlingSettings != null)
			{
				JSONObject throttlingSettingsJson = new JSONObject();
				throttlingSettingsJson.put(AjaxRequestAttributes.J_THROTTLING_ID,
					throttlingSettings.getId());
				throttlingSettingsJson.put(AjaxRequestAttributes.J_THROTTLING_DELAY,
					throttlingSettings.getDelay().getMilliseconds());
				if (throttlingSettings.getPostponeTimerOnUpdate())
				{
					throttlingSettingsJson.put(
						AjaxRequestAttributes.J_THROTTLING_POSTPONE_ON_UPDATE, true);
				}
				attributesJson.put(AjaxRequestAttributes.J_THROTTLING, throttlingSettingsJson);
			}

			postprocessConfiguration(attributesJson, component);
		}
		catch (JSONException e)
		{
			throw new WicketRuntimeException(e);
		}

		String attributesAsJson = attributesJson.toString();

		return attributesAsJson;
	}