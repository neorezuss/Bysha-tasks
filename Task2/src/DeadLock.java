public class DeadLock extends Thread {
    private DeadLock otherDeadLock;

    @Override
    public synchronized void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("(" + Thread.currentThread().getName() + ") Пробуем вызвать дедлок...");
        otherDeadLock.deadLockMethod();

    }

    public void setOtherDeadLock(DeadLock otherDeadLock) {
        this.otherDeadLock = otherDeadLock;
    }

    public synchronized void deadLockMethod() {
        System.out.println("Вызвать дедлок не удалось((");
    }
}
