public abstract class Calc {
    protected int type;

    public Calc(int type) {
        this.type = type;
    }
    @Override
    public String toString() {
        switch (this.type) {
            case 1:
                return "Обычный калькулятор";
            case 2:
                return "Инженерный калькулятор\nв разработке!";
            case 3:
                return "Для программистов\nв разработке!";
            default:
                return "Некорректный тип";
        }
    }
    
}
