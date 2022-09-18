package com.xxx.typhoon.app.forkjoin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.typhoon.app.entity.TyphoonData;
import com.xxx.typhoon.app.entity.TyphoonNews;
import com.xxx.typhoon.app.service.TyphoonDataService;
import com.xxx.typhoon.app.service.TyphoonNewsService;
import com.xxx.typhoon.app.service.impl.TyphoonDataServiceImpl;
import com.xxx.typhoon.app.service.impl.TyphoonNewsServiceImpl;
import lombok.SneakyThrows;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @Author 甘龙
 * @Date 2022/9/10 15:41
 * @PackageName:com.xxx.typhoon.app.forkjoin
 * @ClassName: TyphoonInsertTaskForkJoin
 * @Description:
 * @Version 1.0
 */
public class TyphoonInsertTaskForkJoin extends RecursiveAction {

    private static final int INSERT_MAX_NUM = 1000;

    private List tasks;

    private IService service;




    public TyphoonInsertTaskForkJoin(List tasks, IService service) {
        this.tasks = tasks;
        this.service=service;
    }

    @SneakyThrows
    @Override
    protected void compute() {
        if (tasks.size() <= INSERT_MAX_NUM) {
            service.saveBatch(tasks);
        } else {
            int middle = tasks.size() / 2;
            List dataList1 = tasks.subList(0, middle);
            List dataList2 = tasks.subList(middle, tasks.size());

            TyphoonInsertTaskForkJoin task1 = new TyphoonInsertTaskForkJoin(dataList1,service);
            TyphoonInsertTaskForkJoin task2 = new TyphoonInsertTaskForkJoin(dataList2,service);

            task1.fork();
            task2.fork();

        }
    }
}
