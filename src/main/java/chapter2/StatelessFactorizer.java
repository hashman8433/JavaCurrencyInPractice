package chapter2;


import annotation.ThreadSafe;

import javax.servlet.*;
import java.math.BigInteger;

@ThreadSafe
public class StatelessFactorizer extends AbstractCurrencyServlet implements Servlet {


    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
    }


}
