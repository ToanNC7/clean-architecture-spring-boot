package vn.codingt.clean.core.util.helper;

import com.zaxxer.hikari.HikariDataSource;
import vn.codingt.clean.data.db.jpa.entities.UserData;

import javax.persistence.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public abstract class SaveAllJdbcBatchCallable<T> {
    public List<List<T>> createSubList(List<T> list, int subListSize){
        List<List<T>> listOfSubList = new ArrayList<>();
        for (int i = 0; i < list.size(); i+=subListSize) {
            if(i + subListSize <= list.size()){
                listOfSubList.add(list.subList(i, i + subListSize));
            }else{
                listOfSubList.add(list.subList(i, list.size()));
            }
        }
        return listOfSubList;
    }

    public void saveAllJdbcBatchCallable(HikariDataSource hikariDataSource, int batchSize, List<T> userData, String sql) {
        System.out.println("insert using jdbc batch, threading");
        System.out.print("cp size " + hikariDataSource.getMaximumPoolSize());
        System.out.println(" batch size " + batchSize);

        List<List<T>> listOfBookSub = createSubList(userData, batchSize);
        ExecutorService executorService = Executors.newFixedThreadPool(hikariDataSource.getMaximumPoolSize());

        List<Callable<Integer>> callables = listOfBookSub
                .parallelStream()
                .map(sublist -> (Callable<Integer>) () -> {
                    //System.out.println("Inserting " + sublist.size() + " using callable from thread" + Thread.currentThread().getName());
                    saveAllJdbcBatch(hikariDataSource,sql, batchSize, sublist);
                    return sublist.size();
                })
                .collect(Collectors.toList());
        try {
            List<Future<Integer>> futures = executorService.invokeAll(callables);
            int count = 0;
            for(Future<Integer> future: futures){
                count += future.get();
            }
            System.out.println(count);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public void saveAllJdbcBatch(HikariDataSource hikariDataSource, String sql, int batchSize,  List<T> tList){
        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ){
            int counter = 0;
            for (T t : tList) {
                statement.clearParameters();
                setStatement(statement, t);

                statement.addBatch();
                if ((counter + 1) % batchSize == 0 || (counter + 1) == tList.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public abstract void setStatement(PreparedStatement statement, T t) throws SQLException;
}
