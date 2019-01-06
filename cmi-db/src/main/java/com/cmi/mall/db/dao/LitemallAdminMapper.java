package com.cmi.mall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cmi.mall.db.domain.LitemallAdmin;
import com.cmi.mall.db.domain.LitemallAdminExample;

public interface LitemallAdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    long countByExample(LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int insert(LitemallAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int insertSelective(LitemallAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAdmin selectOneByExample(LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAdmin selectOneByExampleSelective(@Param("example") LitemallAdminExample example, @Param("selective") LitemallAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallAdmin> selectByExampleSelective(@Param("example") LitemallAdminExample example, @Param("selective") LitemallAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    List<LitemallAdmin> selectByExample(LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAdmin selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    LitemallAdmin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallAdmin selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallAdmin record, @Param("example") LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallAdmin record, @Param("example") LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}