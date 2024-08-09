public static Integer getConceptIdForUnits(String units) {
		String appDataDir = OpenmrsUtil.getApplicationDataDirectory();
		Properties props = new Properties();
		String conceptId = null;
		String filePath = new StringBuilder(appDataDir)
				.append(System.getProperty("file.separator"))
				.append(DatabaseUtil.ORDER_ENTRY_UPGRADE_SETTINGS_FILENAME).toString();

		try (FileInputStream fis = new FileInputStream(filePath)) {

			props.load(fis);
			for (Map.Entry prop : props.entrySet()) {
				if (prop.getKey().equals(units)) {
					conceptId = prop.getValue().toString();
					return Integer.valueOf(conceptId);
				}
			}
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