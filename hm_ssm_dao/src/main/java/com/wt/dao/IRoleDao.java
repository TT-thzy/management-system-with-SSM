package com.wt.dao;

import com.wt.pojo.Permission;
import com.wt.pojo.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: mybatisTest
 * @description:
 * @author: Mr.Wang
 * @create: 2021-03-27 17:26
 **/
public interface IRoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results(id = "roleMap",
    value = {
            @Result(id=true,column="id",property="id"),
            @Result(column="roleName",property="roleName"),
            @Result(column="roleDesc",property="roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.wt.dao.IPermissionDao.findById"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;


    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    @Select("select * from role where id=#{roleId}")
    @ResultMap("roleMap")
    Role findById(String roleId);

    @Delete("delete from role where id=#{roleId}")
    void deleteRole(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{rid})")
    List<Permission> findOtherPermission(String roleId);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String s);
}
