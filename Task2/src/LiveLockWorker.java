public class LiveLockWorker {
    private LiveLockWorker otherWorker;
    private boolean active;

    public LiveLockWorker(boolean active) {
        this.active = active;
    }

    public synchronized void work(LiveLockResource resource) {
        while (active) {
            if (resource.getCurrentWorker() != this) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (otherWorker.isActive()) {
                System.out.println("(" + Thread.currentThread().getName() + ") Другой рабочий свободен, передаю работу ему...");
                resource.setCurrentWorker(otherWorker);
                continue;
            }
            System.out.println("(" + Thread.currentThread().getName() + ") Выполняю работу...");
            active = false;
            System.out.println("(" + Thread.currentThread().getName() + ") Завершил работу");
        }
    }

    public void setOtherWorker(LiveLockWorker otherWorker) {
        this.otherWorker = otherWorker;
    }

    public boolean isActive() {
        return active;
    }
}
