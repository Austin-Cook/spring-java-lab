package service.report;

import java.io.PrintStream;

/**
 * Interface requiring all service classes of essential methods.
 */
public interface ReportService {
    /**
     * Method that gets called to run the service and outputs a report.
     * @param stream
     */
    void printReport(PrintStream stream);

}
