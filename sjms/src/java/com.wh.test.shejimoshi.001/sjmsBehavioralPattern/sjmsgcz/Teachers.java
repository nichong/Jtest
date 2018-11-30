package com.wh.test.shejimoshi

import java.util.Observer;
import java.util.Vector;

/**
 * ConcreteSubject(具体目标，Teacher)  
 * 将有关状态存入各ConcreteObserve对象。  
 * 当他的状态发生改变时，向他的各个观察者发出通知。   
 */
public class Teachers implements Subject{

    private String phone;
    private Vector students;

    public Teachers(){
        phone = "";
        students = new Vector();
    }

    public void addWatch(Observers mObserver) {
        students.add(mObserver);
    }

    public void delWatch(Observers mObserver) {
        students.remove(mObserver);
    }

    public void notice() {
        for(int i=0;i<students.size();i++){
            ((Observers)students.get(i)).update();
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notice();//通知观察者（学生）:老师电话更新了
    }
}