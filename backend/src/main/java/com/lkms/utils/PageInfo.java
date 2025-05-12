package com.lkms.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> {
    private final int DEFAUT_PAGESIZE = 10;
    private int pageNum;
    private int totalPage;
    private int pageSize = DEFAUT_PAGESIZE;
    private List<T> data;

    public static <P, V> PageInfo<V> convert(PageInfo<P> pageInfoPO, Class<V> vClass) {
        // 创建Page对象，实际上是一个ArrayList类型的集合
        PageInfo<V> voPageInfo = (PageInfo<V>) PageInfo.builder().pageNum(pageInfoPO.pageNum).totalPage(pageInfoPO.totalPage).pageSize(pageInfoPO.pageSize).build();
        List<V> vos = new ArrayList<>();
        try {
            if (pageInfoPO.getData().size() > 0) {
                Constructor<V> constructor = vClass.getConstructor(pageInfoPO.getData().get(0).getClass());
                for (P p : pageInfoPO.getData()) {
                    V v = null;
                    try {
                        v = constructor.newInstance(p);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    vos.add(v);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        voPageInfo.setData(vos);
        return voPageInfo;
    }
}
