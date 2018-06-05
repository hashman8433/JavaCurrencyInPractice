package chapter5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {

    private final FutureTask<ProductInfo> future =
            new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
                public ProductInfo call() throws Exception {
                    return loadProductInfo();
                }
            });

    private final Thread thread = new Thread(future);

    public void start() { thread.start(); }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException)
                throw (DataLoadException)cause;
            else
                throw launderThrowable(cause);
        }
    }

    private DataLoadException launderThrowable(Throwable cause) {
        return new DataLoadException(cause);
    }

    private class ProductInfo {
    }


    private ProductInfo loadProductInfo() throws InterruptedException {
        Thread.sleep(5000);
        return new ProductInfo();
    }

    private class DataLoadException extends ExecutionException{
        public DataLoadException(Throwable cause) {
            super(cause);
        }
    }

    public static void main(String[] args) throws InterruptedException, DataLoadException {
        Preloader preloader = new Preloader();
        preloader.start();
        System.out.println(preloader.get());
    }
}
