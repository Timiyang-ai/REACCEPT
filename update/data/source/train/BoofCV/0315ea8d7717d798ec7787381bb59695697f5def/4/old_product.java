@SuppressWarnings({"unchecked"})
	public static void checkSubImage(Object testClass,
									 String function,
									 boolean checkEquals,
									 Object... inputParam) {
		try {
			ImageBase[] larger = new ImageBase[inputParam.length];
			ImageBase[] subImg = new ImageBase[inputParam.length];
			Class<?> paramDesc[] = new Class<?>[inputParam.length];
			Object[] inputModified = new Object[inputParam.length];

			for (int i = 0; i < inputParam.length; i++) {
				if (ImageBase.class.isAssignableFrom(inputParam[i].getClass())) {
					ImageBase<?> img = (ImageBase<?>) inputParam[i];

					// copy the original image inside of a larger image
					larger[i] = img._createNew(img.getWidth() + 10, img.getHeight() + 12);
					// extract a sub-image and make it equivalent to the original image.
					subImg[i] = larger[i].subimage(5, 6, 5 + img.getWidth(), 6 + img.getHeight(), null);
					subImg[i].setTo(img);

				}

				// the first time it is called use the original inputs
				inputModified[i] = inputParam[i];
				paramDesc[i] = inputParam[i].getClass();
			}

			// first try it with the original image
			Method m = findMethod(testClass.getClass(), function, paramDesc);

			m.invoke(testClass, inputModified);

			// now try it with the sub-image
			for (int i = 0; i < inputModified.length; i++) {
				if (subImg[i] != null)
					inputModified[i] = subImg[i];
			}
			m.invoke(testClass, inputModified);

			// the result should be the identical
			if (checkEquals) {
				for (int i = 0; i < inputParam.length; i++) {
					if (subImg[i] == null)
						continue;
					assertEquals((ImageBase)inputModified[i], subImg[i], 0);
				}
			}

		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}