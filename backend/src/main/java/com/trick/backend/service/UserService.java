package com.trick.backend.service;

import com.trick.backend.common.result.PageResult;
import com.trick.backend.model.dto.UserQueryDTO;
import com.trick.backend.model.pojo.User;
import com.trick.backend.model.vo.UserVO;

public interface UserService {
    //分页条件查询
    PageResult<User> getUsersByPage(UserQueryDTO queryDTO);

    //根据ID查询
    UserVO getUserById(Integer id);
}
