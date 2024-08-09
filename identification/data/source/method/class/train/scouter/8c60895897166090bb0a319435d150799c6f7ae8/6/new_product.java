protected void applyTelegrafInputConfig() {
		for (Map.Entry<Object, Object> e : property.entrySet()) {
			String key = (String) e.getKey();
			String value = (String) e.getValue();
			if(value == null) {
				continue;
			}

			//eg) input_telegraf_$redis_keyspace$_enabled
			if (key.startsWith(TELEGRAF_INPUT_MEASUREMENT_PREFIX)) { //start with "input_telegraf_$"
				String simplifiedKey = key.substring(TELEGRAF_INPUT_MEASUREMENT_PREFIX_LENGTH); //redis_keyspace$_enabled
				int secondDollar = simplifiedKey.indexOf("$_");
				String measurement = simplifiedKey.substring(0, secondDollar); //redis_keyspace
				String postfix = simplifiedKey.substring(secondDollar + 1);

				TelegrafMasurementConfig tConfig = telegrafInputConfigMap.get(measurement);
				if (tConfig == null) {
					tConfig = new TelegrafMasurementConfig(measurement);
					telegrafInputConfigMap.put(measurement, tConfig);
				}

				if (TELEGRAF_INPUT_MEASUREMENT_ENABLED_POSTFIX.equals(postfix)) {
					try {
						tConfig.setEnabled(Boolean.parseBoolean(value));
					} catch (Exception ignored) {}

				} else if (TELEGRAF_INPUT_MEASUREMENT_DEBUG_ENABLED_POSTFIX.equals(postfix)) {
					try {
						tConfig.setDebugEnabled(Boolean.parseBoolean(value));
					} catch (Exception ignored) {}

				} else if (TELEGRAF_INPUT_MEASUREMENT_COUNTER_MAPPINGS_POSTFIX.equals(postfix)) {
					String[] counterMappings = StringUtil.split(value, ',');
					if (counterMappings == null || counterMappings.length == 0) {
						continue;
					}
					//format: {line-protocol field name}:{scouter counter name}:{display name?}:{unit?}:{hasTotal?}
					Map<String, Counter> counterMappingMap = new HashMap<String, Counter>();
					for (String counterMapping : counterMappings) {
						Counter counter = new Counter();
						String[] split = StringUtil.splitByWholeSeparatorPreserveAllTokens(counterMapping, ':');
						if (split.length >= 2) {
							counterMappingMap.put(split[0], counter);
							counter.setName(split[1]);
						}
						if (split.length >= 3) {
							counter.setDisplayName(split[2]);
						} else {
							counter.setDisplayName(split[1]);
						}
						if (split.length >= 4) {
							counter.setUnit(split[3]);
						} else {
							counter.setUnit("");
						}
						if (split.length >= 5) {
							try {
								counter.setTotal(Boolean.parseBoolean(split[4]));
							} catch (Exception ignored) {}
						}
					}

					if (counterMappingMap.size() > 0) {
						tConfig.setCounterMapping(counterMappingMap);
					}

				} else if (TELEGRAF_INPUT_MEASUREMENT_OBJ_TYPE_BASE_POSTFIX.equals(postfix)) {
					tConfig.setObjTypeBase(value);

				} else if (TELEGRAF_INPUT_MEASUREMENT_OBJ_TYPE_APPEND_TAGS_POSTFIX.equals(postfix)) {
					String[] tags = StringUtil.split(value, ',');
					if (tags == null || tags.length == 0) {
						continue;
					}
					tConfig.setObjTypeAppendTags(Arrays.asList(tags));

				} else if (TELEGRAF_INPUT_MEASUREMENT_OBJ_NAME_BASE_POSTFIX.equals(postfix)) {
					tConfig.setObjNameBase(value);

				} else if (TELEGRAF_INPUT_MEASUREMENT_OBJ_NAME_APPEND_TAGS_POSTFIX.equals(postfix)) {
					String[] tags = StringUtil.split(value, ',');
					if (tags == null || tags.length == 0) {
						continue;
					}
					tConfig.setObjNameAppendTags(Arrays.asList(tags));

				} else if (TELEGRAF_INPUT_MEASUREMENT_HOST_TAG_POSTFIX.equals(postfix)) {
					tConfig.setHostTag(value);

				} else if (TELEGRAF_INPUT_MEASUREMENT_HOST_MAPPINGS_POSTFIX.equals(postfix)) {
					String[] hostMappings = StringUtil.split(value, ',');
					if (hostMappings == null || hostMappings.length == 0) {
						continue;
					}
					Map<String, String> hostMappingMap = new HashMap<String, String>();
					for (String hostAndMap : hostMappings) {
						String[] split = StringUtil.split(hostAndMap, ':');
						if (split.length == 2) {
							hostMappingMap.put(split[0], split[1]);
						}
					}
					tConfig.setHostMapping(hostMappingMap);

				}
			}
		}
	}