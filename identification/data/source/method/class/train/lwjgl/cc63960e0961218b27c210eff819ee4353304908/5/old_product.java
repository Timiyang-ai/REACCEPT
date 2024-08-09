public static DisplayMode setDisplayMode(DisplayMode[] dm, final String[] param) throws Exception {
		
		class FieldAccessor {
			final String fieldName;
			final int order;
			final int preferred;
			final boolean usePreferred;
			FieldAccessor(String fieldName, int order, int preferred, boolean usePreferred) {
				this.fieldName = fieldName;
				this.order = order;
				this.preferred = preferred;
				this.usePreferred = usePreferred;
			}
			int getInt(DisplayMode mode) {
				if ("width".equals(fieldName)) {
					return mode.getWidth();
				}
				if ("height".equals(fieldName)) {
					return mode.getHeight();
				}
				if ("freq".equals(fieldName)) {
					return mode.getFrequency();
				}
				if ("bpp".equals(fieldName)) {
					return mode.getBitsPerPixel();
				}
				throw new IllegalArgumentException("Unknown field "+fieldName);
			}
		}
		
		class Sorter implements Comparator {
			
			final FieldAccessor[] field;
			
			Sorter() throws NoSuchFieldException {
				field = new FieldAccessor[param.length];
				for (int i = 0; i < field.length; i ++) {
					int idx = param[i].indexOf('=');
					if (idx > 0) {
						field[i] = new FieldAccessor(param[i].substring(0, idx), 0, Integer.parseInt(param[i].substring(idx + 1, param[i].length())), true);
					} else if (param[i].charAt(0) == '-') {
						field[i] = new FieldAccessor(param[i].substring(1), -1, 0, false);
					} else {
						field[i] = new FieldAccessor(param[i], 1, 0, false);
					}
				}
			}
			
			/**
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			public int compare(Object o1, Object o2) {
				DisplayMode dm1 = (DisplayMode) o1;
				DisplayMode dm2 = (DisplayMode) o2;
				
				for (int i = 0; i < field.length; i ++) {
					int f1 = field[i].getInt(dm1);
					int f2 = field[i].getInt(dm2);
					
					if (field[i].usePreferred && f1 != f2) {
						if (f1 == field[i].preferred)
							return -1;
						else if (f2 == field[i].preferred)
							return 1;
						else {
							// Score according to the difference between the values
							int absf1 = Math.abs(f1 - field[i].preferred);
							int absf2 = Math.abs(f2 - field[i].preferred);
							if (absf1 < absf2)
								return -1;
							else if (absf1 > absf2)
								return 1;
							else
								continue;
						}
					} else if (f1 < f2)
						return field[i].order;
					else if (f1 == f2)
						continue;
					else
						return -field[i].order;
				}
				
				return 0;
			}
		}
		
		// Sort the display modes
		Arrays.sort(dm, new Sorter());
		
		// Try them out in the appropriate order
		if (LWJGLUtil.DEBUG || DEBUG) {
			System.out.println("Sorted display modes:");
			for (int i = 0; i < dm.length; i ++) {
				System.out.println(dm[i]);
			}
		}
		for (int i = 0; i < dm.length; i ++) {
			try {
				if (LWJGLUtil.DEBUG || DEBUG)
					System.out.println("Attempting to set displaymode: "+dm[i]);
				org.lwjgl.opengl.Display.setDisplayMode(dm[i]);
				return dm[i];
			} catch (Exception e) {
				if (LWJGLUtil.DEBUG || DEBUG) {
					System.out.println("Failed to set display mode to "+dm[i]);
					e.printStackTrace();
				}
			}
		}
		
		throw new Exception("Failed to set display mode.");
	}