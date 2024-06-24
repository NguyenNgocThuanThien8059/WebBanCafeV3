package com.example.webbancafev3.Models;

public class CartItem
{
    private Product product;
    private int Quantity;
    public CartItem(Product product, int quantity)
    {
        this.product = product;
        Quantity = quantity;
    }
    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public int getQuantity()
    {
        return Quantity;
    }

    public void setQuantity(int quantity)
    {
        Quantity = quantity;
    }
}
