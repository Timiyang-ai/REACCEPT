public static List<String> mergeValues(Class<?> type, String cfg, List<String> def) {
	    List<String> defaults = new ArrayList<String>();
        if (def != null) {
            for (String name : def) {
                if (ExtensionLoader.getExtensionLoader(type).hasExtension(name)) {
                    defaults.add(name);
                }
            }
        }
        
	    List<String> names = new ArrayList<String>();
	    
	    // 加入初始值
        String[] configs = (cfg == null || cfg.trim().length() == 0) ? new String[0] : Constants.COMMA_SPLIT_PATTERN.split(cfg);
        for (String config : configs) {
            if(config != null && config.trim().length() > 0) {
                names.add(config);
            }
        }

        // 不包含 -default
        if (! names.contains(Constants.REMOVE_VALUE_PREFIX + Constants.DEFAULT_KEY)) {
            // 加入 插入缺省扩展点
            int i = names.indexOf(Constants.DEFAULT_KEY);
            if (i > 0) {
                names.addAll(i, defaults);
            } else {
                names.addAll(0, defaults);
            }
            names.remove(Constants.DEFAULT_KEY);
        }
        else {
            names.remove(Constants.DEFAULT_KEY);
        }
        
        // 合并-的配置项
        for (String name : new ArrayList<String>(names)) {
            if (name.startsWith(Constants.REMOVE_VALUE_PREFIX)) {
                names.remove(name);
                names.remove(name.substring(1));
            }
        }
        return names;
	}