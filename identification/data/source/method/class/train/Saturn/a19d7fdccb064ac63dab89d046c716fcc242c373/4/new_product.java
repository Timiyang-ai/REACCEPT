public Map<Integer, String> getShardingItemParameters() {
		Map<Integer, String> result = new HashMap<>();
		String value = jobConfiguration.getShardingItemParameters();
		if (Strings.isNullOrEmpty(value)) {
			return result;
		}
		// 解释命令行参数
		String[] shardingItemParameters = value.split(PATTERN);
		Map<String, String> result0 = new HashMap<>(shardingItemParameters.length);
		for (String each : shardingItemParameters) {
			String item = "";
			String exec = "";

			int index = each.indexOf('=');
			if (index > -1) {
				item = each.substring(0, index).trim();
				exec = each.substring(index + 1, each.length()).trim();
				// 去掉前后的双引号"
				if (exec.startsWith(DOUBLE_QUOTE)) {
					exec = exec.substring(1);
				}

				if (exec.endsWith(DOUBLE_QUOTE)) {
					exec = exec.substring(0, exec.length() - 1);
				}
			} else {
				throw new ShardingItemParametersException("Sharding item parameters '%s' format error", value);
			}
			result0.put(item, exec);
		}
		if (isLocalMode()) {
			if (result0.containsKey("*")) {
				result.put(-1, result0.get("*"));
			} else {
				throw new ShardingItemParametersException(
						"Sharding item parameters '%s' format error with local mode job, should be *=xx", value);
			}
		} else {
			Iterator<Map.Entry<String, String>> iterator = result0.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> next = iterator.next();
				String item = next.getKey();
				String exec = next.getValue();
				try {
					result.put(Integer.valueOf(item), exec);
				} catch (final NumberFormatException ex) {
					throw new ShardingItemParametersException("Sharding item parameters key '%s' is not an integer.",
							item);
				}
			}
		}
		return result;
	}