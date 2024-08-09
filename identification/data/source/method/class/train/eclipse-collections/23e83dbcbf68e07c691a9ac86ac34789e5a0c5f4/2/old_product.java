public static <T1, T2> void forEachInBoth(
            T1[] objectArray1,
            T2[] objectArray2,
            Procedure2<T1, T2> procedure)
    {
        if (objectArray1 != null && objectArray2 != null)
        {
            if (objectArray1.length == objectArray2.length)
            {
                int size = objectArray1.length;
                for (int i = 0; i < size; i++)
                {
                    procedure.value(objectArray1[i], objectArray2[i]);
                }
            }
            else
            {
                throw new RuntimeException("Attempt to call forEachInBoth with two arrays of different sizes :"
                        + objectArray1.length + ':' + objectArray2.length);
            }
        }
    }