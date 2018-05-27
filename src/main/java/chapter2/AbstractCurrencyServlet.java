package chapter2;

import javax.servlet.*;
import java.math.BigInteger;

public abstract class AbstractCurrencyServlet implements Servlet {

    public void init(ServletConfig servletConfig) {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public String getServletInfo() {
        return null;
    }

    protected void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    protected BigInteger[] factor(BigInteger i) {
        return new BigInteger[]{i};
    }

    protected BigInteger extractFromRequest(ServletRequest req) {
        return BigInteger.ONE;
    }

    public void destroy() {

    }
}
