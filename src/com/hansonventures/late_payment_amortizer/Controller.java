package com.hansonventures.late_payment_amortizer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    @FXML
    private Button calculateButton;

    @FXML
    private Button printButton;

    @FXML
    private TextField tenantName;

    @FXML
    private TextField tenantAddress;

    @FXML
    private TextField baseRentAmount;

    @FXML
    private TextField lateChargeAmount;

    @FXML
    private TextField annualInterestRate;

    @FXML
    private TextField term;

    @FXML
    private TextField newPayment;

    @FXML
    private TextArea outputArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }

    @FXML
    private void handleCalculate()
    {
        Amortization amortization = new Amortization(Float.parseFloat(lateChargeAmount.getText()), Integer.parseInt(term.getText()), Float.parseFloat(annualInterestRate.getText()));

        float newPaymentAmount = amortization.getPaymentAmount() + Float.parseFloat(baseRentAmount.getText());

        newPayment.setText(String.format("%.2f", newPaymentAmount));
        Tenant tenant = new Tenant(tenantName.getText(), tenantAddress.getText(), Float.parseFloat(baseRentAmount.getText()));

        // Write out the information to the output box.
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");

        outputArea.clear();
        outputArea.setText("RENT LATE PAYMENT AMORTIZATION SCHEDULE\n\n");
        outputArea.appendText(tenant.getName() + '\n');
        outputArea.appendText(tenant.getAddress() + '\n');
        outputArea.appendText("Rent: $" + String.format("%.2f", tenant.getRentAmount()) + '\n');
        outputArea.appendText("Late charge: $" + String.format("%.2f", amortization.getLoanAmount()) + '\n');
        outputArea.appendText("Interest rate: " + String.format("%.1f", amortization.getAnnualInterestRate() * 100) + "%\n");
        outputArea.appendText("Term: " + term.getText() + " months\n");
        outputArea.appendText("Payment: $" + String.format("%.2f", amortization.getPaymentAmount()) + "\n\n");
        outputArea.appendText("Monthly payment plan (as of " + dateFormat.format(now) +"):\n");

        Calendar paymentDate = Calendar.getInstance();

        // We'll assume the first payment starts when the next month's rent is due.
        for (int monthNumber = 1; monthNumber <= amortization.getTermInMonths(); monthNumber++)
        {
            paymentDate.setTime(now);
            paymentDate.set(Calendar.DATE, 1);
            paymentDate.add(Calendar.MONTH, monthNumber);
            outputArea.appendText("Payment #" + monthNumber + " due on " + dateFormat.format(paymentDate.getTime()) + ": $" + String.format("%.2f", newPaymentAmount) + '\n');
        }
    }

    @FXML
    private void handlePrint()
    {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        boolean printed = printerJob.printPage(outputArea);

        if (printed == true)
            printerJob.endJob();
    }

    // Required constructor for FXML.
    public Controller()
    {
    }
}
