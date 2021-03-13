synchronized和lock的区别
1. synchronized是关键字在jvm层面 lock是具体类是api层面的锁
2. synchronized不需要手动释放锁代码执行完会自动让线程释放锁，lock需要手动释放锁，若没有主动释放则可能产生死锁
3. synchronized不可中断除非抛异常或者运行完成，lock可设置超时方法，可以lockInterruptibly()放代码中，调用interrupt()方法中断
4. synchronized非公平锁 ，lock两者都可以有默认是非公平锁
5. synchronized唤醒线程是随机唤醒或者唤醒全部，lock可以精确唤醒




























