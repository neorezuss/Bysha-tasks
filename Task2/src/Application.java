public class Application {
    public static void main(String[] args) {
        deadLockInvocation();
        liveLockInvocation();
        bottleneckInvocation();
    }

    private static void deadLockInvocation() {
        DeadLock deadLock1 = new DeadLock();
        DeadLock deadLock2 = new DeadLock();
        deadLock1.setOtherDeadLock(deadLock2);
        deadLock2.setOtherDeadLock(deadLock1);
        deadLock1.start();
        deadLock2.start();
    }

    private static void liveLockInvocation() {
        LiveLockWorker worker1 = new LiveLockWorker(true);
        LiveLockWorker worker2 = new LiveLockWorker(true);
        worker1.setOtherWorker(worker2);
        worker2.setOtherWorker(worker1);
        LiveLockResource Resource = new LiveLockResource(worker1);
        new Thread(() -> {
            worker1.work(Resource);
        }).start();
        new Thread(() -> {
            worker2.work(Resource);
        }).start();
    }

    private static void bottleneckInvocation() {
        BottleneckShovel shovel = new BottleneckShovel();
        BottleneckDigger digger1 = new BottleneckDigger(shovel);
        BottleneckDigger digger2 = new BottleneckDigger(shovel);
        BottleneckDigger digger3 = new BottleneckDigger(shovel);
        BottleneckDigger digger4 = new BottleneckDigger(shovel);
        BottleneckDigger digger5 = new BottleneckDigger(shovel);
        new Thread(digger1::work).start();
        new Thread(digger2::work).start();
        new Thread(digger3::work).start();
        new Thread(digger4::work).start();
        new Thread(digger5::work).start();
    }

}
