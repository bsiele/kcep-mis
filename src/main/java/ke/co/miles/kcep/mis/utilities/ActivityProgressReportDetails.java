package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;

/**
 *
 * @author siech
 */
public class ActivityProgressReportDetails implements Serializable {

    /**
     * @return the physicalProgressQ1
     */
    public ActivityProgressDetails getPhysicalProgressQ1() {
        return physicalProgressQ1;
    }

    /**
     * @param physicalProgressQ1 the physicalProgressQ1 to set
     */
    public void setPhysicalProgressQ1(ActivityProgressDetails physicalProgressQ1) {
        this.physicalProgressQ1 = physicalProgressQ1;
    }

    /**
     * @return the physicalProgressQ2
     */
    public ActivityProgressDetails getPhysicalProgressQ2() {
        return physicalProgressQ2;
    }

    /**
     * @param physicalProgressQ2 the physicalProgressQ2 to set
     */
    public void setPhysicalProgressQ2(ActivityProgressDetails physicalProgressQ2) {
        this.physicalProgressQ2 = physicalProgressQ2;
    }

    /**
     * @return the physicalProgressQ3
     */
    public ActivityProgressDetails getPhysicalProgressQ3() {
        return physicalProgressQ3;
    }

    /**
     * @param physicalProgressQ3 the physicalProgressQ3 to set
     */
    public void setPhysicalProgressQ3(ActivityProgressDetails physicalProgressQ3) {
        this.physicalProgressQ3 = physicalProgressQ3;
    }

    /**
     * @return the physicalProgressQ4
     */
    public ActivityProgressDetails getPhysicalProgressQ4() {
        return physicalProgressQ4;
    }

    /**
     * @param physicalProgressQ4 the physicalProgressQ4 to set
     */
    public void setPhysicalProgressQ4(ActivityProgressDetails physicalProgressQ4) {
        this.physicalProgressQ4 = physicalProgressQ4;
    }

    /**
     * @return the cummulativePhysicalProgress
     */
    public ActivityProgressDetails getCummulativePhysicalProgress() {
        return cummulativePhysicalProgress;
    }

    /**
     * @param cummulativePhysicalProgress the cummulativePhysicalProgress to set
     */
    public void setCummulativePhysicalProgress(ActivityProgressDetails cummulativePhysicalProgress) {
        this.cummulativePhysicalProgress = cummulativePhysicalProgress;
    }

    /**
     * @return the financialProgressQ1
     */
    public ActivityProgressDetails getFinancialProgressQ1() {
        return financialProgressQ1;
    }

    /**
     * @param financialProgressQ1 the financialProgressQ1 to set
     */
    public void setFinancialProgressQ1(ActivityProgressDetails financialProgressQ1) {
        this.financialProgressQ1 = financialProgressQ1;
    }

    /**
     * @return the financialProgressQ2
     */
    public ActivityProgressDetails getFinancialProgressQ2() {
        return financialProgressQ2;
    }

    /**
     * @param financialProgressQ2 the financialProgressQ2 to set
     */
    public void setFinancialProgressQ2(ActivityProgressDetails financialProgressQ2) {
        this.financialProgressQ2 = financialProgressQ2;
    }

    /**
     * @return the financialProgressQ3
     */
    public ActivityProgressDetails getFinancialProgressQ3() {
        return financialProgressQ3;
    }

    /**
     * @param financialProgressQ3 the financialProgressQ3 to set
     */
    public void setFinancialProgressQ3(ActivityProgressDetails financialProgressQ3) {
        this.financialProgressQ3 = financialProgressQ3;
    }

    /**
     * @return the financialProgressQ4
     */
    public ActivityProgressDetails getFinancialProgressQ4() {
        return financialProgressQ4;
    }

    /**
     * @param financialProgressQ4 the financialProgressQ4 to set
     */
    public void setFinancialProgressQ4(ActivityProgressDetails financialProgressQ4) {
        this.financialProgressQ4 = financialProgressQ4;
    }

    /**
     * @return the cummulativeFinancialProgress
     */
    public ActivityProgressDetails getCummulativeFinancialProgress() {
        return cummulativeFinancialProgress;
    }

    /**
     * @param cummulativeFinancialProgress the cummulativeFinancialProgress to
     * set
     */
    public void setCummulativeFinancialProgress(ActivityProgressDetails cummulativeFinancialProgress) {
        this.cummulativeFinancialProgress = cummulativeFinancialProgress;
    }

    private static final long serialVersionUID = 1L;
    private ActivityProgressDetails physicalProgressQ1;
    private ActivityProgressDetails physicalProgressQ2;
    private ActivityProgressDetails physicalProgressQ3;
    private ActivityProgressDetails physicalProgressQ4;
    private ActivityProgressDetails cummulativePhysicalProgress;
    private ActivityProgressDetails financialProgressQ1;
    private ActivityProgressDetails financialProgressQ2;
    private ActivityProgressDetails financialProgressQ3;
    private ActivityProgressDetails financialProgressQ4;
    private ActivityProgressDetails cummulativeFinancialProgress;

}