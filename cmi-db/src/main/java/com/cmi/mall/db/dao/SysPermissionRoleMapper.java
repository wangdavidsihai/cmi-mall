package com.cmi.mall.db.dao;

import com.cmi.mall.db.domain.SysPermissionRole;
import com.cmi.mall.db.domain.SysPermissionRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysPermissionRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    long countByExample(SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    int deleteByExample(SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    int insert(SysPermissionRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    int insertSelective(SysPermissionRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    SysPermissionRole selectOneByExample(SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    SysPermissionRole selectOneByExampleSelective(@Param("example") SysPermissionRoleExample example, @Param("selective") SysPermissionRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<SysPermissionRole> selectByExampleSelective(@Param("example") SysPermissionRoleExample example, @Param("selective") SysPermissionRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    List<SysPermissionRole> selectByExample(SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    SysPermissionRole selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") SysPermissionRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    SysPermissionRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SysPermissionRole record, @Param("example") SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SysPermissionRole record, @Param("example") SysPermissionRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysPermissionRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Sys_permission_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysPermissionRole record);
}