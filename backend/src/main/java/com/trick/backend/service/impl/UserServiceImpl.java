package com.trick.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trick.backend.common.result.PageResult;
import com.trick.backend.mapper.UserMapper;
import com.trick.backend.model.dto.UserQueryDTO;
import com.trick.backend.model.pojo.User;
import com.trick.backend.model.vo.UserVO;
import com.trick.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //分页条件查询
    @Override
    public PageResult<User> getUsersByPage(UserQueryDTO queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<User> list = userMapper.getAllUsers(queryDTO);
        PageInfo<User> pageInfo = new PageInfo<>(list);

        List<User> records = pageInfo.getList();
        long total = pageInfo.getTotal();

        return new PageResult<>(total, records);
    }

    //根据ID查询
    @Override
    public UserVO getUserById(Integer id) {
        UserVO userVO = new UserVO();
        User user = userMapper.getUserById(id);

        BeanUtils.copyProperties(user, userVO);

        return userVO;
    }
}
