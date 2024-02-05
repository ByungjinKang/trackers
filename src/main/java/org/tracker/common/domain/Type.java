package org.tracker.common.domain;

public enum Type {
    EXPENSE("지출"),
    INCOME("수입");

    private final String koreanName;

    Type(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public static Type fromKoreanName(String koreanName) {
        for (Type type : Type.values()) {
            if (type.getKoreanName().equals(koreanName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No such type: " + koreanName);
    }
}
