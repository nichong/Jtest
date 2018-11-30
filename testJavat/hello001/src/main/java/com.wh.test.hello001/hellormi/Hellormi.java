package com.wh.test.hello001.hellormi;

import java.rmi.Remote;

public interface Hellormi extends Remote {
    void sayHello();
}
