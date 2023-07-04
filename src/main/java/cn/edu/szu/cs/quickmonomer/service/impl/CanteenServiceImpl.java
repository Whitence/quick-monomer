package cn.edu.szu.cs.quickmonomer.service.impl;

import cn.edu.szu.cs.quickmonomer.common.api.PageResult;
import cn.edu.szu.cs.quickmonomer.dto.query.CanteensQueryDto;
import cn.edu.szu.cs.quickmonomer.util.cache.CacheLevel;
import cn.edu.szu.cs.quickmonomer.util.cache.MultiLevelCache;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.log.StaticLog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.szu.cs.quickmonomer.entity.Canteen;
import cn.edu.szu.cs.quickmonomer.service.CanteenService;
import cn.edu.szu.cs.quickmonomer.mapper.CanteenMapper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Whitence
* @description 针对表【canteen(餐厅基本信息表)】的数据库操作Service实现
* @createDate 2023-04-21 18:52:15
*/
@Service
@AllArgsConstructor
public class CanteenServiceImpl extends ServiceImpl<CanteenMapper, Canteen>
    implements CanteenService{

    private MultiLevelCache cache;

    @Override
    public PageResult<Canteen> getCanteens(CanteensQueryDto dto) {

        Page<Canteen> bannerPage = new Page<>(dto.getPageIndex(), dto.getPageSize());

        bannerPage = this.page(bannerPage);

        return PageResult.restPage(bannerPage);

    }

    @Override
    public List<Canteen> canteenList() {

        return cache.getListOrNext(CacheLevel.ONE, this::list, Canteen.class);

    }

    @Override
    public Canteen getCanteenInfo(Long canteenId) {
        return null;
    }
}




