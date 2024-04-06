package serviceApp.service;

import serviceApp.domain.BaseId;

import java.util.List;

public class Validator {
    public static boolean isIdUnique(List<BaseId> idList, int idChecked) {
        for (BaseId idElement : idList) {
            if (idElement.getId() == idChecked) {
                return false;
            }
        }
        return true;
    }
    public static boolean idExists(List<BaseId> idList, int idChecked) {
        for (BaseId idElement : idList) {
            if (idElement.getId() == idChecked) {
                return true;
            }
        }
        return false;
    }
}
