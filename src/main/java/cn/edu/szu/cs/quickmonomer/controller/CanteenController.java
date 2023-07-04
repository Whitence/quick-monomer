package cn.edu.szu.cs.quickmonomer.controller;

import cn.edu.szu.cs.quickmonomer.annotation.ResultWrapIgnore;
import cn.edu.szu.cs.quickmonomer.common.api.PageResult;
import cn.edu.szu.cs.quickmonomer.common.api.Result;
import cn.edu.szu.cs.quickmonomer.dto.query.CanteensQueryDto;
import cn.edu.szu.cs.quickmonomer.entity.Canteen;
import cn.edu.szu.cs.quickmonomer.service.CanteenService;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *  CanteenController
 * @author Whitence
 * @date 2023/4/22 17:12
 * @version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping("/canteen")
@Validated
public class CanteenController {

    private CanteenService canteenService;

    /**
     * @api.name 获取餐厅列表1
     * @param dto
     * @response {@link PageResult<Canteen>}
     */
    @PostMapping("/page")
    public PageResult<Canteen> pageResultCanteen(@RequestBody @Valid CanteensQueryDto dto){

        return canteenService.getCanteens(dto);
    }


    @GetMapping("/list")
    public List<Canteen> list(){
        return canteenService.canteenList();
    }

/**
 * @api.name 获取餐厅信息
 * @param: canteenId
 * @response {@link Canteen}
 */
    @GetMapping()
    public Canteen getOne(@RequestParam("canteenId") @NotNull Long canteenId){
        return canteenService.getCanteenInfo(canteenId);
    }

}
