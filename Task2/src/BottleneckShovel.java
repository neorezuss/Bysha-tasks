public class BottleneckShovel {
    public synchronized void useShovel() {
        System.out.println("(" + Thread.currentThread().getName() + ") Взял лопату и начал копать");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("(" + Thread.currentThread().getName() + ") Закончил копать и отдал лопату");
    }
}
