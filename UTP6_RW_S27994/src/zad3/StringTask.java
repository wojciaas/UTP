package zad3;

import static zad3.TaskState.*;

public class StringTask implements Runnable{
    private final String WORD_TO_REPEAT;
    private final int NUMBER_OF_REPETITIONS;
    private volatile String result = "";
    private TaskState state = CREATED;
    private volatile int counter = 0;
    private Thread thread;

    public StringTask(String wordToRepeat, int numberOfRepetitions) {
        this.WORD_TO_REPEAT = wordToRepeat;
        this.NUMBER_OF_REPETITIONS= numberOfRepetitions;
    }

    @Override
    synchronized public void run() {
        state = RUNNING;
        do {
            result += WORD_TO_REPEAT;
            counter++;
        } while (counter < NUMBER_OF_REPETITIONS && !Thread.currentThread().isInterrupted());

        if (counter == NUMBER_OF_REPETITIONS) state = READY;
    }

    public String getResult() {
        return result;
    }

    public int getCounter() {
        return counter;
    }

    public TaskState getState() {
        return state;
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void abort() {
        state = ABORTED;
        thread.interrupt();
    }

    public boolean isDone() {
        return state.equals(READY) || state.equals(ABORTED);
    }
}
