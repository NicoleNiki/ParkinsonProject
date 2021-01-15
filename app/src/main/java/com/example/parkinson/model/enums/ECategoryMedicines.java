package com.example.parkinson.model.enums;

public enum ECategoryMedicines {
    MEDOPAR("מדופאר"),
    SINMAT("סינמט"),
    Dopamine_Agonists("דופמין אגוניסטים"),
    Amantadine("אמנטדין "),
    Anticholinergics("אנטי כולינרגיות "),
    COMT ("מעכבי האנזים ")
            ;

    public final String name;
    private ECategoryMedicines(String name) {
        this.name = name;
    }
}
