package com.hansonventures.late_payment_amortizer;

/**
 * Class that holds information about the tenant.
 */
final class Tenant
{
    private String name, address;
    private float rentAmount;

    private void setRentAmount(float rentAmount)
    {
        this.rentAmount = rentAmount;
    }

    float getRentAmount()
    {
        return rentAmount;
    }

    String getName()
    {
        return name;
    }

    private void setName(String name)
    {
        this.name = name;
    }

    String getAddress()
    {
        return address;
    }

    private void setAddress(String address)
    {
        this.address = address;
    }

    Tenant(String name, String address, float rentAmount)
    {
        setName(name);
        setAddress(address);
        setRentAmount(rentAmount);
    }
}
