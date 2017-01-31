import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

/**
 * Created by Echo01 on 1/24/2017.
 */
public class TestPrac2 {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

       /* Demo d1 = new Demo();
        Demo d2 = new Demo();
        Demo d3 = new Demo();
        int a[]={1,2,3,4};
        int b[]= {4,3,2,1};
        MyThread4 obj = new MyThread4(d1,b,"Thread-1");
        MyThread4 obj2 = new MyThread4(d1,a,"Thread-2");
        MyThread5 obj3 = new MyThread5(d1,b,"Thread-3");
        MyThread5 obj4 = new MyThread5(d1,a,"Thread-4");

        Thread t1 = new Thread(obj,"Thread-1");
        Thread t2 = new Thread(obj2,"Thread-2");
        Thread t3 = new Thread(obj3,"Thread-3");
        Thread t4 = new Thread(obj4,"Thread-4");

        t1.start();t2.start();t3.start();t4.start();*/
        MyThread6 thread2=new MyThread6();
        Thread t2 = new Thread(thread2);
        t2.start();
        MyThread6 thread1=new MyThread6();
        Thread t = new Thread(thread1);
        t.start();
        t.sleep(1000);
        //t2.sleep(1000);
        synchronized (thread2){
            System.out.println("thread2 trying to call wait() method");
            try{
                thread2.wait(0,1);
                System.out.println("thread2 got notification");
                System.out.println(thread2.Total);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        synchronized (thread1){
            System.out.println("thread1 trying to call wait() method");
            try{
                thread1.wait(1000);
                System.out.println("thread1 got notifiaction");
                System.out.println(thread1.Total);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
class MyThread4 implements Runnable {
    //Thread t = new Thread();
    Demo d1;int a[];String name;
    MyThread4(Demo d1,int a[],String name){
        this.a=a;
        this.d1=d1;
        this.name=name;
        Thread.currentThread().setName(name);
            /*t=new Thread(this);
            System.out.println(t.getName()+" started ");
            try{
                t[i].sleep(500);
            }catch(InterruptedException e){
                System.out.println("Someone butted in.Scram");
            }*/
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is executing method1");
        d1.method(a);
    }
}
class MyThread5 implements Runnable {
    //Thread t = new Thread();
    Demo d1;int a[];String name;
    MyThread5(Demo d1,int a[],String name){
        this.a=a;
        this.d1=d1;
        this.name = name;
        Thread.currentThread().setName(name);
            /*t=new Thread(this);
            System.out.println(t.getName()+" started ");
            try{
                t[i].sleep(500);
            }catch(InterruptedException e){
                System.out.println("Someone butted in.Scram");
            }*/
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is executing method2");
        d1.method2(a);
    }
}
class Demo {
    // If the method method() is not synchronized then it will result in an irregular output
     public synchronized void method(int a[]){
         int A[] = a;
        int j=3;
        int k=0;
        for(int i=0;i<1000000;i++){
            while(k<j){
                int temp = A[k];
                A[k]=A[j];
                A[j]=temp;
                k++;
                j--;
            }
        }
        for(int i=0;i<4;i++){
            System.out.print(A[i]+" ");
            try{
                Thread.sleep(500);
                System.out.print(Thread.currentThread().getName()+" is sleeping ");
            }catch (InterruptedException e){
                System.out.println("Thread interrupted");
            }
        }
        System.out.println();
    }
    public void method2(int a[]){
        int A[]=a;
        int j=3;
        int k=0;
        synchronized (this){
            for(int i=0;i<1000000;i++){
                while(k<j){
                    int temp = A[k];
                    A[k]=A[j];
                    A[j]=temp;
                    k++;
                    j--;
                }
            }
            for(int i=0;i<4;i++){
                System.out.print(A[i]+" ");
                try{
                    Thread.sleep(500);
                    System.out.print(Thread.currentThread().getName()+" is sleeping ");
                }catch (InterruptedException e){
                    System.out.println("Thread interrupted");
                }
            }
        }
        System.out.println();
    }
}
class MyThread6 implements Runnable{
    int Total =0;
    @Override
    public void run() {
        synchronized (this){
            System.out.println("Child thread starts calculation");
            for(int i=0;i<101;i++){
                Total = Total + i;
            }
            this.notifyAll();
        }
    }
}

