package com.hansonventures.late_payment_amortizer;

/**
 * This class does the amortization calculations. It makes the assumption that interest is compounded monthly.
 */
final class Amortization
{
    private float annualInterestRate; // The interest rate as a decimal (0.08 for 8%)
    private float paymentAmount = 0, loanAmount;
    private int termInMonths;

    float getAnnualInterestRate()
    {
        return annualInterestRate;
    }

    private void setAnnualInterestRate(float annualInterestRate)
    {
        // If the interest rate is not in decimal form make it that way.
        if (annualInterestRate >= 1)
            this.annualInterestRate = annualInterestRate / 100;
        else
            this.annualInterestRate = annualInterestRate;
    }

    float getPaymentAmount()
    {
        // Only calculate the payment if it hasn't been calculated already.
        if (paymentAmount == 0)
            calculatePayment();

        return paymentAmount;
    }

    int getTermInMonths()
    {
        return termInMonths;
    }

    private void setTermInMonths(int termInMonths)
    {
        this.termInMonths = termInMonths;
    }

    private void setLoanAmount(float loanAmount)
    {
        this.loanAmount = loanAmount;
    }

    float getLoanAmount()
    {
        return loanAmount;
    }

    private void calculatePayment()
    {
        float periodicInterestRate = annualInterestRate / termInMonths;

        paymentAmount = (float)(loanAmount * (periodicInterestRate + (periodicInterestRate / (Math.pow(1 + periodicInterestRate, termInMonths) - 1))));
    }

    Amortization(float loanAmount, int termInMonths, float annualInterestRate)
    {
        setLoanAmount(loanAmount);
        setTermInMonths(termInMonths);
        setAnnualInterestRate(annualInterestRate);
    }
}
