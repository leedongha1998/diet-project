package lee.dongha.dietproject.diet.entity;

public enum MealTime {
    BREAKFAST(0,"아침"),
    LUNCH(1,"점심"),
    DINNER(2,"저녁"),
    SNACK(4,"간식");

    private final int order;
    private final String korean;


    MealTime(int order, String korean) {
        this.order = order;
        this.korean = korean;
    }

    public int getOrder() {
        return order;
    }

    public String getKorean() {
        return korean;
    }

}
