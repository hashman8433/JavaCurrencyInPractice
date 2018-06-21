package util;

public class LotteryUtil {

    /**
     * count institution return Rates
     * A VS B
     * @param winOdds winner odds  (A win)
     * @param tieOdds tie odds (A = B)
     * @param loseOdds lose odds (B win)
     * @return
     */
    public static double returnRates(double winOdds, double tieOdds, double loseOdds) {
        // expect earn 100  RMB
        // odds

        // expect win for earn 100
        double betsWin = 100d/winOdds;

        // ...tie...100
        double betsTie = 100d/tieOdds;

        // ...lose...100
        double betsLost = 100d/loseOdds;

        // Return Rates
        return 100d/(betsLost + betsTie + betsWin);

    }

    public static void countBetsMethod(Institution institution0, Institution institution1,
                                     double expectMoney) {

        // we have 8 bet method
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {

                    /** example
                     * i = 0; j = 0; k = 0 ===> 1A 1B 1C
                     * i = 1; j = 0; k = 0 ===> 2A 1B 1C
                     * i = 0; j = 1; k = 0 ===> 1A 2B 1C
                     */
                    Institution institutionA = getInstitution(institution0, institution1, i);
                    Institution institutionB = getInstitution(institution0, institution1, j);
                    Institution institutionC = getInstitution(institution0, institution1, k);

                    // bet A
                    double betAMoney = 100d / institutionA.getA().getOdds();
                    double betBMoney = 100d / institutionB.getB().getOdds();
                    double betCMoney = 100d / institutionC.getC().getOdds();
                    double earnMoney = (betAMoney + betBMoney + betCMoney) - 100d;

                    if (earnMoney > 0)
                        System.out.println(
                            "bet institution " + institutionA.getInstitutioName() + " Team " + institutionA.getA().getStakeName() + " payMoney " + betAMoney + "\n" +
                            "bet institution " + institutionB.getInstitutioName() + " Team " + institutionB.getB().getStakeName() + " payMoney " + betBMoney + "\n" +
                            "bet institution " + institutionC.getInstitutioName() + " Team " + institutionC.getC().getStakeName() + " payMoney " + betCMoney + "\n" +
                            "earn monry " + earnMoney + "\n" +
                            "+++++++ \n\n"
                        );
                }
            }
        }
    }


    private static Institution getInstitution(Institution institution0, Institution institution1, int selectNum) {
        return (selectNum == 0) ? institution0 : institution1;
    }


    static class Stake{

        private String stakeName;
        private double odds;
        private double winningProbability;

        public Stake(String stakeName, double odds) {
            this.odds = odds;
            this.stakeName = stakeName;
        }

        public String getStakeName() {
            return stakeName;
        }

        public void setStakeName(String stakeName) {
            this.stakeName = stakeName;
        }

        public double getOdds() {
            return odds;
        }

        public void setOdds(double odds) {
            this.odds = odds;
        }

        public double getWinningProbability() {
            return winningProbability;
        }

        public void setWinningProbability(double winningProbability) {
            this.winningProbability = winningProbability;
        }
    }


    /**
     * Bookmaker institution
     */
    static class Institution {
        private String institutioName;
        private Stake A;
        private Stake B;
        private Stake C;

        public Institution(String institutioName, Stake a, Stake b, Stake c) {
            this.institutioName = institutioName;
            A = a;
            B = b;
            C = c;
        }

        public String getInstitutioName() {
            return institutioName;
        }

        public void setInstitutioName(String institutioName) {
            this.institutioName = institutioName;
        }

        public Stake getA() {
            return A;
        }

        public void setA(Stake a) {
            A = a;
        }

        public Stake getB() {
            return B;
        }

        public void setB(Stake b) {
            B = b;
        }

        public Stake getC() {
            return C;
        }

        public void setC(Stake c) {
            C = c;
        }
    }

    public static void main(String[] arsg) {
        // ++++ institution A +++
        Stake stake1A = new Stake("A win", 12.50);
        Stake stake1B = new Stake("A tie B", 2.74);
        Stake stake1C = new Stake("B win", 1.64);
        Institution institution0 = new Institution("china bookMarker",
                stake1A, stake1B, stake1C);

        Stake stake2A = new Stake("A win", 12.50);
        Stake stake2B = new Stake("A tie B", 5.90);
        Stake stake2C = new Stake("B win", 1.10);
        Institution institution1 = new Institution("china bookMarker",
                stake2A, stake2B, stake2C);

        countBetsMethod(institution0, institution1, 100);

    }
}
