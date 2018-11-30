package com.wh.test.shejimoshi

public class TShirt extends Decorator {
    @Override
    public void show(){
        System.out.println("穿T-Shirt");
        super.show();
    }
}
