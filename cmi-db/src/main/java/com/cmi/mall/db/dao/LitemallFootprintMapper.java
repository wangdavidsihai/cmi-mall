package com.cmi.mall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cmi.mall.db.domain.LitemallFootprint;
import com.cmi.mall.db.domain.LitemallFootprintExample;

public interface LitemallFootprintMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    long countByExample(LitemallFootprintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallFootprintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    int insert(LitemallFootprint record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    int insertSelective(LitemallFootprint record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallFootprint selectOneByExample(LitemallFootprintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallFootprint selectOneByExampleSelective(@Param("example") LitemallFootprintExample example, @Param("selective") LitemallFootprint.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallFootprint> selectByExampleSelective(@Param("example") LitemallFootprintExample example, @Param("selective") LitemallFootprint.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    List<LitemallFootprint> selectByExample(LitemallFootprintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallFootprint selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallFootprint.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    LitemallFootprint selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallFootprint selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallFootprint record, @Param("example") LitemallFootprintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallFootprint record, @Param("example") LitemallFootprintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallFootprint record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallFootprint record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") LitemallFootprintExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_footprint
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}