package com.yc.log.base;

import com.yc.log.constant.WebConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class BaseService{
	/**
	 * 批量存储
	 * @param list 数据集合
	 * @param save 消费处理函数式接口,做存储操作
	 * @param saveOperator 操作类
	 * @param batch 一次插入的数量
	 * @param <T> 数据类型
	 * @param <TOperator> 操作类的类型
	 */
	protected <T, TOperator> void batchSaveData(List<T> list, BiConsumer<TOperator, List<T>> save, TOperator saveOperator, int batch) {
   	 	if(list == null || list.isEmpty()  || save == null) {
   	 		return;
   	 	}
   	 	if(batch <= 0) {
   	 		batch = WebConstant.BATCH_SIZE;
   	 	}
   	 	int size = list.size();
   	 	List<T> resultList = new ArrayList<T>(batch);
   	 	for(int i = 0; i < size; i++) {
	   		T item = list.get(i);
	   		if(i % batch == 0 && !resultList.isEmpty()) {
	   			save.accept(saveOperator, resultList);
	   			resultList.clear();
	   		}
	   		resultList.add(item);
   	 	}
   	 	if(!resultList.isEmpty()) {
   	 		save.accept(saveOperator, resultList);
   	 	}
    }
	
}
