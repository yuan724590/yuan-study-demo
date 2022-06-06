package yuan.study.demo.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yuan.study.demo.entity.Book;
import yuan.study.demo.service.Java8DemoService;

import java.util.*;
import java.util.concurrent.*;


@Slf4j
@Service
public class Java8DemoServiceImpl implements Java8DemoService {

    @Override
    public String completableFuture(){
        //CompletableFuture默认是forkJoinPool

        //使用线程池执行, supplyAsync有返回值
        supplyAsync();
        //使用线程池执行, runAsync没有返回值
        runAsync();

        //分别执行之后(包含thenCombine的线程)合并两次的结果 (Async: 不使用主线程, 而是再起一条线程来执行)
        thenCombine();
        thenCombineAsync();
        //分别执行两条线程之后, 对两个结果进行处理 (Async: 不使用主线程, 而是再起一条线程来执行)
        thenAcceptBoth();
        thenAcceptBothAsync();

        //在线程处理完成之后进行处理, 有入参,有出参 (Async: 不使用主线程, 而是再起一条线程来执行)
        thenApply();
        thenApplyAsync();
        //在线程处理完成之后进行处理, 有入参,有出参, 在发生异常时也可以执行 (Async: 不使用主线程, 而是再起一条线程来执行)
        handle();
        handleAsync();
        //在线程处理完成之后进行处理, 有入参,无出参, 在发生异常时也可以执行
        // 非Async: 可能使用主线程, 也可能是子线程, Async: 不使用主线程, 而是再起一条线程来执行
        whenComplete();
        whenCompleteAsync();
        //只有在发生异常时才可以执行
        exceptionally();
        //覆盖原本的异常为设置异常
        obtrudeException();
        //覆盖原本的值
        obtrudeValue();
        //在线程处理完成之后进行处理, 有入参,无出参 (Async: 不使用主线程, 而是再起一条线程来执行)
        thenAccept();
        thenAcceptAsync();
        //在线程处理完成之后进行处理, 无入参,无出参 (Async: 不使用主线程, 而是再起一条线程来执行)
        thenRun();
        thenRunAsync();
        //在两个线程任一完成之后, 进行处理, 有入参, 无出参 (Async: 不使用主线程, 而是再起一条线程来执行)
        acceptEither();
        acceptEitherAsync();
        //在两个线程任一完成之后, 进行处理, 有入参, 有出参 (Async: 不使用主线程, 而是再起一条线程来执行)
        applyToEither();
        applyToEitherAsync();
        //在两个线程都完成之后, 进行处理, 无入参, 无出参 (Async: 不使用主线程, 而是再起一条线程来执行)
        runAfterBoth();
        runAfterBothAsync();
        //在两个线程任一完成之后, 进行处理, 无入参, 无出参 (Async: 不使用主线程, 而是再起一条线程来执行)
        runAfterEither();
        runAfterEitherAsync();
        //所有线程都执行完毕之后结束, 无出参
        allOf();
        //任一线程都执行完毕之后结束, 有出参
        anyOf();
        //优雅的循环生成线程
        loopAsync();
        return "success";
    }

    private void loopAsync(){
        List<Book> list = new ArrayList<>();
        list.add(new Book(1, "a"));
        list.add(new Book(2, "b"));
        CompletableFuture.allOf(list.stream()
                .map(e -> CompletableFuture.runAsync(() -> e.setName("c")))
                .toArray(CompletableFuture[]::new))
                .join();
    }

    /**
     * 任一线程都执行完毕之后结束, 有出参
     */
    private void anyOf(){
        CompletableFuture future2 = CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2;
                }),CompletableFuture.supplyAsync(() ->{
                    return 4;
                })
        );
        System.out.println("anyOf结果:" + future2.join());
    }

    /**
     * 所有线程都执行完毕之后结束, 无出参
     */
    private void allOf(){
        CompletableFuture future2 = CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2;
                }),CompletableFuture.supplyAsync(() ->{
                    return 4;
                })
        );
        System.out.println("allOf结果:" + future2.join());

        ExecutorService pool = Executors.newFixedThreadPool(2);
        CompletableFuture future = CompletableFuture.allOf(
                CompletableFuture.runAsync(()->{
                    int i = 0;
                    while(i < 10){
                        System.out.println("activeCount:" + ((ThreadPoolExecutor) pool).getActiveCount());
                        sleep(500);
                        i++;
                    }
                }),
                CompletableFuture.runAsync(()->{
                    int a = 1;
                    sleep(2000);
                }, pool),
                CompletableFuture.runAsync(()->{
                    int a = 2;
                    sleep(3000);
                }, pool)
        );
        future.join();
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println("activeCount:" + ((ThreadPoolExecutor) pool).getActiveCount());
    }


    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在两个线程任一完成之后, 进行处理, 无入参, 无出参 (Async: 不使用主线程, 而是再起一条线程来执行)
     */
    private void runAfterEitherAsync(){
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            int a = 2;
            return a++;
        });
        CompletableFuture<Integer> runAfterBoth = future1.runAfterEitherAsync(future2, () -> {
            System.out.println("runAfterEitherAsync结果:" + 1);
        });
        runAfterBoth.join();
    }

    /**
     * 在两个线程任一完成之后, 进行处理, 无入参, 无出参
     */
    private void runAfterEither(){
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            int a = 2;
            return a++;
        });
        CompletableFuture<Integer> runAfterBoth = future1.runAfterEither(future2, () -> {
            System.out.println("runAfterEither结果:" + 1);
        });
        runAfterBoth.join();
    }

    /**
     * 在两个线程都完成之后, 进行处理, 无入参, 无出参 (Async: 不使用主线程, 而是再起一条线程来执行)
     */
    private void runAfterBothAsync(){
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            int a = 2;
            return a++;
        });
        CompletableFuture<Integer> runAfterBoth = future1.runAfterBothAsync(future2, () -> {
            System.out.println("runAfterBoth结果:" + 1);
        });
        runAfterBoth.join();
    }

    /**
     * 在两个线程都完成之后, 进行处理, 无入参, 无出参
     */
    private void runAfterBoth(){
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            int a = 2;
            return a++;
        });
        CompletableFuture<Integer> runAfterBoth = future1.runAfterBoth(future2, () -> {
            System.out.println("runAfterBoth结果:" + 1);
        });
        runAfterBoth.join();
    }

    /**
     * 在两个线程任一完成之后, 进行处理, 有入参, 有出参 (不使用主线程, 而是再起一条线程来执行)
     */
    private void applyToEitherAsync(){
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            int a = 2;
            return a++;
        });
        CompletableFuture<Integer> either = future1.applyToEither(future2, (e) -> {
            System.out.println("applyToEitherAsync结果:" + e);
            return e;
        });
        either.join();
    }

    /**
     * 在两个线程任一完成之后, 进行处理, 有入参, 有出参
     */
    private void applyToEither(){
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            int a = 2;
            return a++;
        });
        CompletableFuture<Integer> either = future1.applyToEither(future2, (e) -> {
            System.out.println("applyToEither结果:" + e);
            return e;
        });
        either.join();
    }

    /**
     * 在两个线程任一完成之后, 进行处理, 有入参, 无出参 (不使用主线程, 而是再起一条线程来执行)
     */
    private void acceptEitherAsync(){
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        });
        CompletableFuture<Integer> either = future1.acceptEitherAsync(future2, (e) -> {
            System.out.println("acceptEither结果:" + e);
        });
        either.join();
    }

    /**
     * 在两个线程任一完成之后, 进行处理, 有入参, 无出参
     */
    private void acceptEither(){
        CompletableFuture future1 = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        });
        CompletableFuture future2 = CompletableFuture.supplyAsync(() -> {
            int a = 2;
            return a++;
        });
        CompletableFuture<Integer> either = future1.acceptEither(future2, (e) -> {
            System.out.println("acceptEither结果:" + e);
        });
        either.join();
    }

    /**
     * 在线程处理完成之后进行处理, 无入参,无出参 (不使用主线程, 而是再起一条线程来执行)
     */
    private void thenRunAsync(){
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        }).thenRunAsync(() ->{
            int b = 2;
            System.out.println("thenAcceptAsync结果:" + b++);
        });
        future.join();
    }

    /**
     * 在线程处理完成之后进行处理, 无入参,无出参
     */
    private void thenRun(){
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        }).thenRun(() ->{
            int b = 2;
            System.out.println("thenAcceptAsync结果:" + b++);
        });
        future.join();
    }

    /**
     * 在线程处理完成之后进行处理, 有入参,无出参 (不使用主线程, 而是再起一条线程来执行)
     */
    private void thenAcceptAsync(){
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        }).thenAcceptAsync((e) ->{
            int b = 2;
            System.out.println("thenAcceptAsync结果:" + b++);
        });
        future.join();
    }

    /**
     * 在线程处理完成之后进行处理, 有入参,无出参
     */
    private void thenAccept(){
        CompletableFuture future = CompletableFuture.supplyAsync(() -> {
            int a = 1;
            return a++;
        }).thenAccept((e) ->{
            int b = 2;
            System.out.println("thenAccept结果:" + b++);
        });
        future.join();
    }

    /**
     * 覆盖原本的值
     */
    private void obtrudeValue(){
        CompletableFuture future = CompletableFuture.supplyAsync(() ->{
            int a = 2;
            return a;
        });
        future.obtrudeValue(1);
        System.out.println("obtrudeException结果为:" + future.join());
    }

    /**
     * 覆盖原本的异常为设置异常
     */
    private void obtrudeException(){
        try{
            CompletableFuture future = CompletableFuture.supplyAsync(() ->{
                int a = 1 / 0;
                return a;
            });
            future.obtrudeException(new NullPointerException());
            future.join();
        }catch (Exception e){
            System.out.println("obtrudeException结果为:" + e.getMessage());
        }

    }

    /**
     * 只有在发生异常时才可以执行
     */
    private void exceptionally(){
        CompletableFuture future = CompletableFuture.supplyAsync(() ->{
            int a = 1 / 0;
            return a;
        }).exceptionally(e -> {
            System.out.println("exceptionally结果为:" + e);
            return 1;
        });
        future.join();
    }

    /**
     * 在线程处理完成之后进行处理, 有入参,无出参, 在发生异常时也可以执行(不使用主线程, 而是再起一条线程来执行)
     */
    private void whenCompleteAsync(){
        CompletableFuture future = CompletableFuture.supplyAsync(() ->{
            int a = 1;
            return a + 1;
        }).whenCompleteAsync((response, e) -> {
            System.out.println("whenComplete结果为:" + response);
        });
        future.join();
    }

    /**
     * 在线程处理完成之后进行处理, 有入参,无出参, 在发生异常时也可以执行(可能使用主线程, 也可能是子线程)
     */
    private void whenComplete(){
        CompletableFuture future = CompletableFuture.supplyAsync(() ->{
            int a = 1;
            return a + 1;
        }).whenComplete((response, e) -> {
            System.out.println("whenComplete结果为:" + response);
        });
        future.join();
    }

    /**
     * 在线程处理完成之后进行处理, 有入参,有出参, 在发生异常时也可以执行(不使用主线程, 而是再起一条线程来执行)
     */
    private void handleAsync(){
        CompletableFuture future = CompletableFuture.supplyAsync(() ->{
            int a = 1;
            return a + 1;
        }).handleAsync((response, e) -> {
            int b = 2;
            return response + b;
        });
        System.out.println("handleAsync结果为:" + future.join());
    }

    /**
     * 在线程处理完成之后进行处理, 有入参,有出参, 在发生异常时也可以执行
     */
    private void handle(){
        CompletableFuture future = CompletableFuture.supplyAsync(() ->{
            int a = 1;
            return a + 1;
        }).handle((response, e) -> {
            int b = 2;
            return response + b;
        });
        System.out.println("handle结果为:" + future.join());
    }

    /**
     * 在线程处理完成之后进行处理, 有入参,有出参(不使用主线程, 而是再起一条线程来执行)
     */
    private void thenApplyAsync(){
        CompletableFuture future = CompletableFuture.supplyAsync(() ->{
            int a = 1;
            return a + 1;
        }).thenApplyAsync((e) -> {
            int b = 2;
            return e + b;
        });
        System.out.println("thenApplyAsync结果为:" + future.join());
    }

    /**
     * 在线程处理完成之后进行处理, 有入参,有出参
     */
    private void thenApply(){
        CompletableFuture future = CompletableFuture.supplyAsync(() ->{
            int a = 1;
            return a + 1;
        }).thenApply((e) -> {
            int b = 2;
            return e + b;
        });
        System.out.println("thenApply结果:" + future.join());
    }

    /**
     * 分别执行之后(包含thenCombine的线程)合并两次的结果(不使用主线程, 而是再起一条线程来执行)
     */
    private void thenCombineAsync(){
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                int a = 5;
                a++;
                return a;
            }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
                int b = 3;
                return b++;
            }), (s1, s2) -> s1 + s2);
            System.out.println("thenCombineAsync结果:" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分别执行两条线程之后, 对两个结果进行处理 (不使用主线程, 而是再起一条线程来执行)
     */
    private void thenAcceptBoth(){
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 5;
            a++;
            return a;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int a = 5;
            a++;
            return a;
        });
        future1.thenAcceptBoth(future2, (value1, value2) -> {
            System.out.println("thenAcceptBoth结果:" + value1 + value2);
        });
    }

    /**
     * 分别执行之后合并两次的结果(不使用主线程, 而是再起一条线程来执行)
     */
    private void thenAcceptBothAsync(){
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            int a = 5;
            a++;
            return a;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            int a = 5;
            a++;
            return a;
        });
        future1.thenAcceptBoth(future2, (value1, value2) -> {
            System.out.println("thenAcceptBothAsync结果:" + value1 + value2);
        });
    }

    /**
     * 分别执行之后(包含thenCombine的线程)合并两次的结果
     */
    private void thenCombine(){
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                int a = 5;
                a++;
                return a;
            }).thenCombine(CompletableFuture.supplyAsync(() -> {
                int b = 3;
                return b++;
            }), (s1, s2) -> s1 + s2);
            System.out.println("thenCombine结果:" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用线程池执行, supplyAsync有返回值
     */
    private void supplyAsync(){
        try {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                int a = 5;
                a++;
                return a;
            });
            System.out.println("supplyAsync结果:" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用线程池执行, runAsync没有返回值
     */
    private void runAsync(){
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            int a = new Random().nextInt(10);
            System.out.println("runAsync结果:" + a++);
        });
        future.join();
    }

    public void forkJoinPool(){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(() -> {
            int j = 0;
            for(int i = 0; i < 3; i++){
                j += i;
            }
            System.out.println("forkJoinPool.execute" + j);
        });
    }

    @Override
    public String limit(){
        List<Book> list = new ArrayList<>();
        for(int i = 0; i <5; i++){
            list.add(new Book(i, "aaa"));
        }

        final List<Book> list1 = list.stream().sorted(Comparator.comparing(Book::getId).reversed()).filter(e -> e.getId() < 3).limit(1).collect(Collectors.toList());
        list1.add(new Book(5, "aaa"));
        int a = 1;
        return "";
    }
}
