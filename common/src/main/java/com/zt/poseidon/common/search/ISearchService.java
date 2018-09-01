package com.zt.poseidon.common.search;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/24
 */
public interface ISearchService<T,R>{

    R search(Consumer<? super T> consumer,String... args);

    <V> V search(Consumer<? super T> consumer,Function<? super R,? extends V> function,String... args);

    R search(T t);

}
