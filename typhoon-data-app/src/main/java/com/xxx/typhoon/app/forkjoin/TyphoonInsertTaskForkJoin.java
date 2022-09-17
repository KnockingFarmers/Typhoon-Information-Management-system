package com.xxx.typhoon.app.forkjoin;

import com.xxx.typhoon.app.entity.TyphoonData;
import com.xxx.typhoon.app.entity.TyphoonNews;
import com.xxx.typhoon.app.service.TyphoonDataService;
import com.xxx.typhoon.app.service.TyphoonNewsService;
import com.xxx.typhoon.app.service.impl.TyphoonDataServiceImpl;
import com.xxx.typhoon.app.service.impl.TyphoonNewsServiceImpl;

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
public class TyphoonInsertTaskForkJoin<T> extends RecursiveAction {

    private static final int INSERT_MAX_NUM = 500;

    private List<T> tasks;

    private Class<T> toEntity;




    public TyphoonInsertTaskForkJoin(List<T> tasks, Class<T> toEntity) {
        this.tasks = tasks;
        this.toEntity=toEntity;
    }

    @Override
    protected void compute() {
        if (tasks.size() <= INSERT_MAX_NUM) {
            if (toEntity.isAssignableFrom(TyphoonData.class)){
                TyphoonDataService dataService = new TyphoonDataServiceImpl();

                dataService.saveBatch((Collection<TyphoonData>) tasks);
            }else {
                TyphoonNewsService newsService=new TyphoonNewsServiceImpl();
                newsService.saveBatch((Collection<TyphoonNews>) tasks);
            }
        } else {
            int middle = tasks.size() / 2;
            List<T> dataList1 = tasks.subList(0, middle);
            List<T> dataList2 = tasks.subList(middle, tasks.size());

            TyphoonInsertTaskForkJoin task1 = new TyphoonInsertTaskForkJoin(dataList1,toEntity);
            TyphoonInsertTaskForkJoin task2 = new TyphoonInsertTaskForkJoin(dataList2,toEntity);

            task1.fork();
            task2.fork();

        }
    }
}
