public static Integer getConceptIdForUnits(String units) {
		String appDataDir = OpenmrsUtil.getApplicationDataDirectory();
		Properties props = new Properties();
		String conceptId = null;
		try {
			FileInputStream fis = new FileInputStream(appDataDir + System.getProperty("file.separator")
			        + DatabaseUtil.ORDER_ENTRY_UPGRADE_SETTINGS_FILENAME);
			props.load(fis);
			for (Map.Entry prop : props.entrySet()) {
				if (prop.getKey().equals(units)) {
					conceptId = prop.getValue().toString();
					return Integer.valueOf(conceptId);
				}
			}
			fis.close();
		}
		catch (NumberFormatException e) {
			throw new APIException("upgrade.settings.file.invalid.mapping", new Object[] { units, conceptId }, e);
		}
		catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				throw new APIException("upgrade.settings.unable.find.file", new Object[] { appDataDir }, e);
			} else {
				throw new APIException(e);
			}
		}
		
		throw new APIException("upgrade.settings.file.not.have.mapping", new Object[] { units });
	}