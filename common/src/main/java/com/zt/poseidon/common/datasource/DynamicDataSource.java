package com.zt.poseidon.common.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class DynamicDataSource extends AbstractRoutingDataSource {
    private  static  Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    private DataSourceProps dataSourceProps;

    private List<String> slaveKeys=new ArrayList<>();

    public static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<String>();

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceHolder.get();
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
    }


    public  void  setDS(DSType type){
        if (type==DSType.WRITE){
            dataSourceHolder.set("master");
            return;
        }else{
            if (slaveKeys.isEmpty()){
                Map<String, DataSourceProps.MyDataSource> slaves = dataSourceProps.getSlaves();
                if (slaves != null && slaves.size() > 0){
                    slaveKeys.addAll(slaves.keySet());
                }else {
                    Integer slaveNums = dataSourceProps.getSlaveNums();
                    if (slaveNums != null && slaveNums > 0){
                        for (int i = 1; i <= slaveNums; i++) {
                            String key="slave_"+i;
                            slaveKeys.add(key);
                        }
                    }
                }
            }
            if (slaveKeys == null||slaveKeys.isEmpty()){
                dataSourceHolder.set("master");
            }else {
                int num = new Random().nextInt(slaveKeys.size());
                String ds=slaveKeys.get(num);
                dataSourceHolder.set(ds);
            }
        }
    }

    public  void clearDS(){
        dataSourceHolder.remove();
    }


    public DataSourceProps getDataSourceProps() {
        return dataSourceProps;
    }

    public void setDataSourceProps(DataSourceProps dataSourceProps) {
        this.dataSourceProps = dataSourceProps;
    }
}
