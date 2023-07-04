package cn.edu.szu.cs.quickmonomer.service;

import cn.edu.szu.cs.quickmonomer.common.api.PageResult;
import cn.edu.szu.cs.quickmonomer.dto.query.CanteensQueryDto;
import cn.edu.szu.cs.quickmonomer.entity.Canteen;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Whitence
* @description 针对表【canteen(餐厅基本信息表)】的数据库操作Service
* @createDate 2023-04-21 18:52:15
*/
public interface CanteenService extends IService<Canteen> {

    PageResult<Canteen> getCanteens(CanteensQueryDto dto);

    List<Canteen> canteenList();


    Canteen getCanteenInfo(Long canteenId);
}
