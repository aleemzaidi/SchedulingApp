package azaidi6.schedulingapp.model;

public class Report {

    String reportValue;
    int quantity;

    /**
     * Constructor for Report object
     * @param reportValue string of reportValue
     * @param quantity int of quantity
     */
    public Report(String reportValue, int quantity) {
        this.reportValue = reportValue;
        this.quantity = quantity;
    }

    /**
     * Used to get quantity of report
     * @return returns int of quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Used to get quantity of report
     * @param quantity int quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Used to get reportValue
     * @return returns string of reportValue
     */
    public String getReportValue() {
        return reportValue;
    }

    /**
     * Used to set reportValue
     * @param reportValue string of reportValue
     */
    public void setReportValue(String reportValue) {
        this.reportValue = reportValue;
    }
}
