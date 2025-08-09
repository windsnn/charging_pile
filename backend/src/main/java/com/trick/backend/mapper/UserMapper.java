package com.trick.backend.mapper;

import com.trick.backend.model.dto.UserAddAndUpdateDTO;
import com.trick.backend.model.dto.UserQueryDTO;
import com.trick.backend.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    //分页条件查询用户（昵称，手机号），排序方式默认为创建时间倒序
    //获取完整的数据，使用pageHelper进行分页
    List<User> getAllUsers(UserQueryDTO queryDTO);

    //根据ID获取单个用户数据
    User getUserById(Integer id);

    //根据openId查询用户ID
    Integer getUserByOpenId(String openid);

    //添加微信用户 回显ID
    Integer addUser(UserAddAndUpdateDTO dto);
}
