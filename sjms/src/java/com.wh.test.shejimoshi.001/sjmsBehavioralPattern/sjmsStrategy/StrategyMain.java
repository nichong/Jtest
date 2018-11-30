package com.wh.test.shejimoshi.001.sjmsStrategy

//策略模式仅仅封装算法,具体用哪个算法由调用方自己决定
public class StrategyMain {
    public static void main(String[] args) {
        com.wh.test.shejimoshi.Strategy strategy = new com.wh.test.shejimoshi.StrategyC();
        //Strategy strategy = new StrategyB();
        Context context = new Context(strategy);
        context.printPrice(100);
    }
}