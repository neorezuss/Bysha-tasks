public class BottleneckDigger {
    private BottleneckShovel shovel;

    public BottleneckDigger(BottleneckShovel shovel) {
        this.shovel = shovel;
    }

    public void work() {
        System.out.println("(" + Thread.currentThread().getName() + ") Рабочий день начат");
        shovel.useShovel();
        System.out.println("(" + Thread.currentThread().getName() + ") Рабочий день окончен");
    }
}
