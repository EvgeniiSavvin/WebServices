package utils;

import model.PersonRequest;

public class PersonUtils {
    public static boolean allFieldsSet(PersonRequest request) {
        return request.isFirstNameSet() &&
                request.isLastNameSet() &&
                request.isHeightSet() &&
                request.isAgeSet() &&
                request.isMaleSet();
    }

    public static boolean stringIsNullOrEmpty(String string) {
        if(string == null) return true;
        return string.isEmpty();
    }
}
