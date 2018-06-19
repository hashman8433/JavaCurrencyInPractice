package chapter6;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class QuoteTask implements Callable<TravelQuote> {

    private final TravelCompany company;
    private final TravelInfo travelInfo;
    ExecutorService exec;

    QuoteTask(TravelCompany company, TravelInfo travelInfo) {
        this.company = company;
        this.travelInfo = travelInfo;
    }


    @Override
    public TravelQuote call() throws Exception {
        return company.solicitQuote(travelInfo);
    }


    public TravelQuote solicitQuote(TravelQuote travelQuote,
                                        Set<TravelCompany> companyies,
                                        Comparator<TravelQuote> ranking,
                                        long time, TimeUnit unit) {
        List<QuoteTask> tasks = new ArrayList<QuoteTask>();
        for (TravelCompany company : companyies)
            tasks.add(new QuoteTask(company, travelInfo));


        List<Future<TravelQuote>> futures =
                exec.involeAll(tasks, time, unit);

        List<TravelQuote> quotes =
                new ArrayList<TravelQuote>(tasks.size());
        Iterator<QuoteTask> taskIter = tasks.iterator();
        for (Future<TravelQuote> f : futures) {
            QuoteTask task = taskIter.next();

            try {
                quotes.add(f.get());
            } catch (InterruptedException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch (ExecutionException e) {
                quotes.add(task.getTimeoutQuote(e));
            }
        }
        return null;
    }

    /**
     * time out travelQuote
     * @param e
     * @return
     */
    private TravelQuote getTimeoutQuote(ExecutionException e) {
        return new TravelQuote();
    }

    /**
     * failure excute quote
     * @param cause
     * @return
     */
    private TravelQuote getFailureQuote(Throwable cause) {
        return new TravelQuote();
    }

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo) {
        return null;
    }


    private class TravelCompany {

        public TravelQuote solicitQuote(TravelInfo travelInfo) {
            return null;
        }
    }



    private class TravelInfo {
    }

    private class ExecutorService {
        public List<Future<TravelQuote>> involeAll(List<QuoteTask> tasks, long time, TimeUnit unit) {
            return null;
        }
    }
}

class TravelQuote {
}

