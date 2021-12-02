public class LiveLockResource {
    private LiveLockWorker currenеWorker;

    public LiveLockResource(LiveLockWorker currentLiveLockWorker) {
        this.currenеWorker = currentLiveLockWorker;
    }

    public LiveLockWorker getCurrentWorker() {
        return currenеWorker;
    }

    public synchronized void setCurrentWorker(LiveLockWorker currentLiveLockWorker) {
        this.currenеWorker = currentLiveLockWorker;
    }
}
