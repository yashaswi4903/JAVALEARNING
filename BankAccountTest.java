import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private double balance;
    private final Lock lock = new ReentrantLock();
    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    
    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
        } finally {
            lock.unlock();
        }
    }
    
    public void withdraw(double amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
            } else {
                System.out.println("Insufficient funds!");
            }
        } finally {
            lock.unlock();
        }
    }
    
    public double getBalance() {
        return balance;
    }
}

public class BankAccountTest {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(1000);
        
        Thread[] threads = new Thread[10];
        
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> account.deposit(100), "Depositor-" + i);
            threads[i + 5] = new Thread(() -> account.withdraw(50), "Withdrawer-" + i);
        }
        
        for (Thread thread : threads) {
            thread.start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        System.out.println("Final balance: " + account.getBalance());
    }
}