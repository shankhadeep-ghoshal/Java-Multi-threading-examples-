/**
 * Created by Echo01 on 2/2/2017.
 * This module explains a deadlock situation(A deadlock example)
 * To get past the deadlock situation,just remove one synchronized keyword because hte synchronized keyword is the only reason for a deadlock situation.
 * Due to this,while using synchronized keyword we have to take special care.
 */
public class Deadlock extends Thread {
    A a = new A();
    B b = new B();
    public void m1(){
        System.out.println("main thread starts execution");
        this.start();
        a.d1(b);
    }

    @Override
    public void run() {
        b.d2(a);
    }

    public static void main(String args[])throws Exception{
        //Deadlock d = new Deadlock();
        //d.m1();
        MyThread thread = new MyThread();
        thread.setDaemon(true); // Set the child thread to daemon
        thread.start();
        //The output is uncertain as main finishes execution and the daemon child has to be terminated
        // as per the rule regarding daemon threads.
        System.out.println("Main thread execution finished");
    }
}
class A {
    public synchronized void d1(B b){
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        b.last();
    }
    public synchronized void last(){
        System.out.println("Class A 's last() method");
    }

}
class B {
    public synchronized void d2(A a){
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        a.last();
    }
    public synchronized void last(){
        System.out.println("Class B 's last() method");
    }

}
/*
*The following class is a part of an example of Daemon threads */

class MyThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Execute!!!!!");
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
