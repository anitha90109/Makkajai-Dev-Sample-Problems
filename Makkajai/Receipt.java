package com.springboot.controller;
/*
 * Problem 1: Sales Tax
Basic sales tax is applicable at a rate of 10% on all goods, except books, food,
and medical products that are exempt. Import duty is an additional sales tax
applicable on all imported goods at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items
and their price (including tax), finishing with the total cost of the items,
and the total amounts of sales taxes paid.  The rounding rules for sales tax are
that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to
the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping baskets...
INPUT:
Input 1:
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

Input 2:
1 imported box of chocolates at 10.00
1 imported bottle of perfume at 47.50

Input 3:
1 imported bottle of perfume at 27.99
1 bottle of perfume at 18.99
1 packet of headache pills at 9.75
1 box of imported chocolates at 11.25

Output 1
Output 1:
1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83

Output 2:
1 imported box of chocolates: 10.50
1 imported bottle of perfume: 54.65
Sales Taxes: 7.65
Total: 65.15

Output 3:
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68
 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Item {
    String name;
    double price;
    boolean isImported;
    boolean isExempt;

    public Item(String name, double price, boolean isImported, boolean isExempt) {
        this.name = name;
        this.price = price;
        this.isImported = isImported;
        this.isExempt = isExempt;
    }

    public double getSalesTax() {
        double taxRate = 0; 
        if (isImported) {
            taxRate = 0.05; // Additional import tax
        }	
        if (isExempt) {
        	 // No tax for exempt items
        }else {
        	taxRate += 0.10; // Basic sales tax
        }
        return roundUp(price * taxRate);
    }

    private double roundUp(double value) {
        return Math.ceil(value * 20) / 20; // Round up to the nearest
    }

    public double getTotalPrice() {
        return price + getSalesTax();
    }
}

public class Receipt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        List<Item> items = new ArrayList<>();
        
        // Input 1
        items.add(new Item("book", 12.49, false, true));
        items.add(new Item("music CD", 14.99, false, false));
        items.add(new Item("chocolate bar", 0.85, false, true));
        
        printReceipt(items);
        
        // Clear list for next input
        items.clear();
        
        // Input 2
        items.add(new Item("imported box of chocolates", 10.00, true, true));
        items.add(new Item("imported bottle of perfume", 47.50, true, false));
        
        printReceipt(items);
        
        // Clear list for next input
        items.clear();
        
        // Input 3
        items.add(new Item("imported bottle of perfume", 27.99, true, false));
        items.add(new Item("bottle of perfume", 18.99, false, false));
        items.add(new Item("packet of headache pills", 9.75, false, true));
        items.add(new Item("imported box of chocolates", 11.25, true, true));
        
        printReceipt(items);
        
        scanner.close();
    }

    private static void printReceipt(List<Item> items) {
        double totalSalesTax = 0;
        double totalCost = 0;

        for (Item item : items) {
            totalSalesTax += item.getSalesTax();
            totalCost += item.getTotalPrice();
            System.out.printf("%s: %.2f\n", item.name, item.getTotalPrice());
        }

        System.out.printf("Sales Taxes: %.2f\n", totalSalesTax);
        System.out.printf("Total: %.2f\n\n", totalCost);
    }
}

