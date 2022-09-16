package com.xxx.typhoon.app.forkjoin;

import com.xxx.typhoon.app.entity.TyphoonData;
import com.xxx.typhoon.app.service.TyphoonDataService;
import com.xxx.typhoon.app.service.impl.TyphoonDataServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @Author 甘龙
 * @Date 2022/9/10 15:41
 * @PackageName:com.xxx.typhoon.app.forkjoin
 * @ClassName: MyTaskForkJoin
 * @Description: TODO
 * @Version 1.0
 */
public class TyphoonDataTaskForkJoin extends RecursiveAction {

    private static final int INSERT_NUM = 200;

    private List<TyphoonData> tasks;


    public TyphoonDataTaskForkJoin(List<TyphoonData> tasks) {
        this.tasks = tasks;
    }

    @Override
    protected void compute() {
        if (tasks.size() <= INSERT_NUM) {
            TyphoonDataService dataService = new TyphoonDataServiceImpl();
            dataService.saveBatch(tasks);
        } else {
            int middle = tasks.size() / 2;
            List<TyphoonData> dataList1 = tasks.subList(0, middle);
            List<TyphoonData> dataList2 = tasks.subList(middle, tasks.size());

            TyphoonDataTaskForkJoin task1 = new TyphoonDataTaskForkJoin(dataList1);
            TyphoonDataTaskForkJoin task2 = new TyphoonDataTaskForkJoin(dataList2);

            task1.fork();
            task2.fork();

        }
    }
}
