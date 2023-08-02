/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.se1715.group4.gasstore.dto;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;

/**
 *
 * @author CC
 */
public class Cart {
    private Vector<Item>  items ;
    
    public Cart(){
        items = new Vector<>();
    }

    public Vector<Item> getItems() {
        return items;
    }

    public void setItems(Vector<Item> items) {
        this.items = items;
    }
    
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    public Item getItemById(int id){
        for(Item i:items){
            if(i.getProduct().getProductId()==id){
                return i;
            }
        }
        return null;
    }
    
    public String getMoneyById(int id){
        double t=0;
        Item i = getItemById(id);
        t += (Math.round(i.getUnitPrice() - i.getUnitPrice() * i.getProduct().getDiscount().getDiscount())/1000)*1000*i.getQuantity();
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedMoney = currencyFormatter.format(t);
        return formattedMoney;
    }
    
    public String getSubTotal(){
        double t=0;
        for(Item i:items)
            t += (Math.round(i.getUnitPrice() - i.getUnitPrice() * i.getProduct().getDiscount().getDiscount())/1000)*1000*i.getQuantity();
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedMoney = currencyFormatter.format(t);
        return formattedMoney;
    }


    
    public void addItem(Item t){
        if(getItemById(t.getProduct().getProductId())!=null){
            Item m=getItemById(t.getProduct().getProductId());
            m.setQuantity(m.getQuantity()+t.getQuantity());
        }else
            items.add(t);
    }
    public void removeItem(int id){
        if(getItemById(id)!=null){
            items.remove(getItemById(id));
        }
    }
    public double getTotalMoney(){
        double t=0;
        for(Item i:items)
            t+=(i.getQuantity()*(i.getUnitPrice() - i.getUnitPrice()*i.getProduct().getDiscount().getDiscount()));
        return t;
    }
    private Product getProductById(int id,Vector<Product> list){
        for(Product i:list){
            if(i.getProductId()==id)
                return i;
        }
        return null;
    }
    
    public Cart(String txt,Vector<Product> list){
        items=new Vector<>();
        try{
        if(txt!=null && txt.length()!=0){
            String[] s=txt.split("/");
            for(String i:s){
                String[] n=i.split(":");
                int id=Integer.parseInt(n[0]);
                int quantity=Integer.parseInt(n[1]);
                Product p=getProductById(id, list);
                Item t=new Item(quantity, p.getUnitPrice(), p);
                addItem(t);
            }
        }
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
    }
}
