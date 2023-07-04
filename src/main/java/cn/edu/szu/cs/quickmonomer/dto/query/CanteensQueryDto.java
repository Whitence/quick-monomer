package cn.edu.szu.cs.quickmonomer.dto.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;

/**
 * @description: TODO
 * @author whitence
 * @date 2023/4/21 18:57
 * @version 1.0
 */

@Data
@AllArgsConstructor
public class CanteensQueryDto {
    /**
     * 餐厅名
     */
    @NonNull
    private String canteenName;
    /**
     * 当前页
     * @mock @pick([1,2,3])
     */
    @Min(1)
    private Integer pageIndex;
    /**
     * 页大小
     * @mock @nature(1)
     */
    @Min(1)
    private Integer pageSize;
}
