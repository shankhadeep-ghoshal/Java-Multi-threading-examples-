/**
 * Created by Echo01 on 1/3/2017.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;

public class TestPrac1  {
    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
       /* String s = "Hellow";
        String s1 = new String(s);
        String s2;
        s2=s1;
        String s3 = new String(s2);
        StringBuffer s4 = new StringBuffer();
        s4.append(s);
        System.out.println(s4.capacity());

        System.out.println(s1==s2);
        System.out.println(s2==s3); */
        //CharSequence sequence = new StringBuilder("ASDAS DASDS");
        /*Integer x = Integer.parseInt(br.readLine());
        Integer y = Integer.parseInt(br.readLine());
        Integer z;
        z = x+y;
        System.out.println(z);*/

        /*String s = "Hellow";
        s=s.replace('l','K');
        System.out.println(s);
        StringBuilder s1 = new StringBuilder("hellow");
        s1.append(" world");
        s1.setCharAt(4,'Z');
        s1.deleteCharAt(5);
        s1.reverse();
        System.out.println(s1);

       for(String K:sequence.toString().split(" ")){
           System.out.println(K);
       }
        System.out.println(sequence);*/
        /*Thread t = Thread.currentThread();

        System.out.println("current thread: " + t);

        t.setName("Name Changed");
        System.out.println("After name change: " + t);

        try{
            for(int i=5;i>0;i--){
                System.out.println(i);
                Thread.sleep(2000);
            }
        }catch (InterruptedException e){
            System.out.println("Main thread interrupted");
        }*/
       /* Thread k = Thread.currentThread();
        //k.setPriority(4);
        //MyThread t = new MyThread();
        //t.start();
        //t.setPriority(6);
        //MyThread2 t2 = new MyThread2();
        MyThread3 t3 = new MyThread3();
        Thread l = new Thread(t3);
        l.start();
        try{
            l.join(500);
        }catch (InterruptedException e){
            k.interrupt();
            System.out.println("Process interrupted");
        }
        //l.setPriority(9);
        System.out.println(l.getName()+" "+l.getPriority());
        k.interrupt();
        if(k.isInterrupted()) System.out.println("Main Thread interrupted");
        if(k.isAlive())System.out.println("Main thread is alive");

        System.out.println(k.getName());
        System.out.println(k.getPriority());
        for(int i =0;i<5;i++){
            System.out.println(1000*100);
        }*/

       /* int n = Integer.parseInt(br.readLine());
        System.out.println(n);*/
    }
}

class MyThread extends Thread{
    // Not advisable to use this as the option to use inheritance is chopped off
    @Override
    public void run() {
        MyThread2 x = new MyThread2();
        Thread t = new Thread(x);
        //this.setPriority(6);
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority());
        try{
            t.join(500);
            for(int i=0;i<5;i++){
                System.out.println(i+"Child thread");
            }
        }catch(InterruptedException e){
            System.out.println("Execution Interrupted");
        }
    }
}

class MyThread2 implements Runnable{
    Thread t;
    MyThread2(){
        t=new Thread(this);
        //t.setPriority(6);
        t.start();
    }
    //Recommended approach for creating threads
    @Override
    public void run() {
        MyThread3 m3 = new MyThread3();
        Thread k = new Thread(m3);
        try{
            k.join();
            for(int i=0;i<5;i++){
                t.yield();
                System.out.println("Second thread");
            }
        }catch(InterruptedException e){
            System.out.println("Exec stop");
        }
       // System.out.println("Numbered thread starts");
    }
}

class MyThread3 implements Runnable{

    @Override
    public void run() {
        MyThread t = new MyThread();
        t.start();
        for (int i=0;i<5;i++){
            try{
                Thread.sleep(2000);
                System.out.println("Third thread");
            }catch(InterruptedException e){
                System.out.println("Thread interrupted");
            }
        }
    }
}

