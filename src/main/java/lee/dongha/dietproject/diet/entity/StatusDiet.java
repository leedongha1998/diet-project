package lee.dongha.dietproject.diet.entity;

public enum StatusDiet {
    HIGH("최상"),MID("중"),LOW("하");

    private final String korean;

    StatusDiet(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
