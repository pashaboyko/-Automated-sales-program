package sample.pojo;

import sample.pojo.Product;


public class Tech  extends Product
{
    private int warranty;
    private String color;
    public Tech (int id, String n, String bar, double pr,String photo, int w, String col)
    {
        super(id,n,bar,pr,photo);
        this.warranty=w;
        this.color=col;
    }
    public Tech()
    {
        super();
    }
    public Tech(Tech another)
    {
        super(another);
        this.warranty=another.warranty;
        this.color=another.color;
    }
    public int getWarranty()
    {
        return warranty;
    }
    public void setWarranty(int w)
    {
        this.warranty=w;
    }
    public String getColor()
    {
        return color;
    }
    public void setColor(String c)
    {
        this.color=c;
    }


}