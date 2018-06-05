package chapter5;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CellularAutomata {
    private final Board maniBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.maniBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count,
                new Runnable() {
                    public void run() {
                        maniBoard.commiteNewValues();
                    }
                });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++)
            workers[i] = new Worker(maniBoard.getSubBoard(count));
    }

    private class Board {
        int count;
        public Board(int count) {
            this.count = count;
        }

        public void commiteNewValues() {
        }

        public Board getSubBoard(int count) {
            return new Board(count);
        }

        public boolean hasConverged() {
            return false;
        }

        public int getMaxX() {
            return 0;
        }

        public void setNewValue(int x, int y, Object o) {

        }

        public void waitForConvergence() {
        }
    }

    private class Worker implements Runnable{
        private final Board board;
        public Worker(Board board) {
            this.board = board;
        }

        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; x < board.getMaxX(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    return;
                } catch (BrokenBarrierException e) {
                    return;
                }
            }
        }

        private Object computeValue(int x, int y) {
            return null;
        }
        
        public void start() {
            for (int i = 0; i < workers.length; i++) 
                new Thread(workers[i]).start();
            maniBoard.waitForConvergence();
        }
    }
}
