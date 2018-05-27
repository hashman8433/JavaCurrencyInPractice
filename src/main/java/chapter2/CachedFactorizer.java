package chapter2;

import annotation.GuardeBy;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;

public class CachedFactorizer extends AbstractCurrencyServlet implements Servlet {

    @GuardeBy("this") private BigInteger lastNumber;
    @GuardeBy("this") private BigInteger[] lastFactors;
    @GuardeBy("this") private long hits;
    @GuardeBy("this") private long cacheHits;

    public synchronized long getHits() { return hits; }
    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / (double) hits;
    }

    public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = lastFactors;

        synchronized (this) {
            ++hits;
            if (i.equals(lastNumber)) {
                ++cacheHits;
                factors = lastFactors;
            }
        }

        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factor(i);
            }
        }
        encodeIntoResponse(resp, factors);
    }
}
