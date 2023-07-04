package cn.edu.szu.cs.quickmonomer.common.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: 分页包装类
 * @author hjc
 * @date 2023/3/14 23:49
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 当前页码
     */
    private long pageIndex;
    /**
     * 页大小
     */
    private long pageSize;
    /**
     * 总页数
     */
    private long totalPages;
    /**
     * 总数
     */
    private long total;
    /**
     * 结果
     */
    private List<T> list;

    public static <T> PageResult<T> restPage(Page<T> pageInfo) {

        PageResult<T> page = new PageResult<>();
        page.setPageIndex(pageInfo.getCurrent());
        page.setPageSize(pageInfo.getSize());
        page.setTotalPages(pageInfo.getPages());
        page.setTotal(pageInfo.getTotal());
        page.setList(pageInfo.getRecords());
        return page;
    }

    public <R> PageResult<R> convert(Function<T, R> function){

        return new PageResult<>(
                pageIndex,
                pageSize,
                totalPages,
                total,
                list.stream().map(function).collect(Collectors.toList())
        );
    }

    public static <T> PageResult<T> wrapList(List<T> list,Integer pageIndex,Integer pageSize){
        int totalPages = list.size()%pageSize == 0?list.size()/pageSize: list.size()/pageSize+1;
        return new PageResult<>(
                pageIndex,
                pageSize,
                totalPages,
                list.size(),
                list.stream().skip((long) pageSize *(pageIndex-1)).limit(pageSize).collect(Collectors.toList())
        );
    }

    public static <T> PageResult<T> toPage(
            Integer pageNum,
            Integer pageSize,
            Integer totalPage,
            Integer total,
            List<T> list
    ){
        return new PageResult<>(
                pageNum,
                pageSize,
                totalPage,
                total,
                list
        );
    }
}
