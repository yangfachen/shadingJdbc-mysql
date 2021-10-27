package com.yang.mysql.controller;

import com.google.common.collect.Lists;
import com.yang.mysql.common.RestItemResult;
import com.yang.mysql.dao.UserDao;
import com.yang.mysql.domain.Product;
import com.yang.mysql.domain.User;
import com.yang.mysql.mapper.custom.CustomUserMapper;
import com.yang.mysql.mapper.generated.ProductMapper;
import com.yang.mysql.mapper.generated.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductMapper productMapper;


    @GetMapping("/get/{id}")
    public RestItemResult get(@PathVariable("id") long id){

        Product product = productMapper.selectByPrimaryKey(id);

        return new RestItemResult(product);
    }


    @PostMapping("/add")
    public String add(){

        for (int i = 2; i < 1000; i++) {
            Product product = new Product();
            product.setId((long)i);
            product.setProductSeq("productSeq"+i);
            product.setSaleId("saleId"+i);
            product.setCreateTime(LocalDateTime.now());
            product.setSkuId("skuId"+i);
            product.setSpuId("spuId"+i);
            product.setStoreId((long)i);
            product.setUpdateTime(LocalDateTime.now());
            productMapper.insertSelective(product);

        }
        return "success";
    }


}
