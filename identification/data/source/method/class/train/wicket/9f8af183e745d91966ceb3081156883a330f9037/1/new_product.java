protected final CharSequence renderAjaxAttributes(final Component component,
		AjaxRequestAttributes attributes)
	{
		JSONObject attributesJson = new JSONObject();

		try
		{
			attributesJson.put("u", getCallbackUrl());
			Method method = attributes.getMethod();
			if (Method.POST == method)
			{
				attributesJson.put("m", method);
			}

			if (component instanceof Page == false)
			{
				String componentId = component.getMarkupId();
				attributesJson.put("c", componentId);
			}

			String formId = attributes.getFormId();
			if (Strings.isEmpty(formId) == false)
			{
				attributesJson.put("f", formId);
			}

			if (attributes.isMultipart())
			{
				attributesJson.put("mp", true);
			}

			String submittingComponentId = attributes.getSubmittingComponentName();
			if (Strings.isEmpty(submittingComponentId) == false)
			{
				attributesJson.put("sc", submittingComponentId);
			}

			String indicatorId = findIndicatorId();
			if (Strings.isEmpty(indicatorId) == false)
			{
				attributesJson.put("i", indicatorId);
			}

			for (IAjaxCallListener ajaxCallListener : attributes.getAjaxCallListeners())
			{
				if (ajaxCallListener != null)
				{
					CharSequence beforeHandler = ajaxCallListener.getBeforeHandler(component);
					appendListenerHandler(beforeHandler, attributesJson, "bh", BEFORE_HANDLER_FUNCTION_TEMPLATE);

					CharSequence beforeSendHandler = ajaxCallListener.getBeforeSendHandler(component);
					appendListenerHandler(beforeSendHandler, attributesJson, "bsh", BEFORE_SEND_HANDLER_FUNCTION_TEMPLATE);

					CharSequence afterHandler = ajaxCallListener.getAfterHandler(component);
					appendListenerHandler(afterHandler, attributesJson, "ah", AFTER_HANDLER_FUNCTION_TEMPLATE);

					CharSequence successHandler = ajaxCallListener.getSuccessHandler(component);
					appendListenerHandler(successHandler, attributesJson, "sh", SUCCESS_HANDLER_FUNCTION_TEMPLATE);

					CharSequence failureHandler = ajaxCallListener.getFailureHandler(component);
					appendListenerHandler(failureHandler, attributesJson, "fh", FAILURE_HANDLER_FUNCTION_TEMPLATE);

					CharSequence completeHandler = ajaxCallListener.getCompleteHandler(component);
					appendListenerHandler(completeHandler, attributesJson, "coh", COMPLETE_HANDLER_FUNCTION_TEMPLATE);

					CharSequence precondition = ajaxCallListener.getPrecondition(component);
					appendListenerHandler(precondition, attributesJson, "pre", PRECONDITION_FUNCTION_TEMPLATE);
				}
			}

			JSONObject extraParameters = new JSONObject();
			Iterator<Entry<String, Object>> itor = attributes.getExtraParameters()
				.entrySet()
				.iterator();
			while (itor.hasNext())
			{
				Entry<String, Object> entry = itor.next();
				String name = entry.getKey();
				Object value = entry.getValue();
				extraParameters.accumulate(name, value);
			}
			if (extraParameters.length() > 0)
			{
				attributesJson.put("ep", extraParameters);
			}

			List<CharSequence> dynamicExtraParameters = attributes.getDynamicExtraParameters();
			if (dynamicExtraParameters != null)
			{
				for (CharSequence dynamicExtraParameter : dynamicExtraParameters)
				{
					String func = String.format(DYNAMIC_PARAMETER_FUNCTION_TEMPLATE, dynamicExtraParameter);
					JsonFunction function = new JsonFunction(func);
					attributesJson.append("dep", function);
				}
			}

			if (attributes.isAsynchronous() == false)
			{
				attributesJson.put("async", false);
			}

			String[] eventNames = attributes.getEventNames();
			if (eventNames.length == 1)
			{
				attributesJson.put("e", eventNames[0]);
			}
			else
			{
				for (String eventName : eventNames)
				{
					attributesJson.append("e", eventName);
				}
			}

			AjaxChannel channel = attributes.getChannel();
			if (channel != null)
			{
				attributesJson.put("ch", channel);
			}

			if (attributes.isAllowDefault())
			{
				attributesJson.put("ad", true);
			}

			Duration requestTimeout = attributes.getRequestTimeout();
			if (requestTimeout != null)
			{
				attributesJson.put("rt", requestTimeout.getMilliseconds());
			}

			boolean wicketAjaxResponse = attributes.isWicketAjaxResponse();
			if (wicketAjaxResponse == false)
			{
				attributesJson.put("wr", false);
			}

			String dataType = attributes.getDataType();
			if (AjaxRequestAttributes.XML_DATA_TYPE.equals(dataType) == false)
			{
				attributesJson.put("dt", dataType);
			}

			ThrottlingSettings throttlingSettings = attributes.getThrottlingSettings();
			if (throttlingSettings != null)
			{
				JSONObject throttlingSettingsJson = new JSONObject();
				throttlingSettingsJson.put("id", throttlingSettings.getId());
				throttlingSettingsJson.put("d", throttlingSettings.getDelay().getMilliseconds());
				if (throttlingSettings.getPostponeTimerOnUpdate())
				{
					throttlingSettingsJson.put("p", true);
				}
				attributesJson.put("tr", throttlingSettingsJson);
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