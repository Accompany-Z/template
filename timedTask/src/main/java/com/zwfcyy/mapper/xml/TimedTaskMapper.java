package com.zwfcyy.mapper.xml;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author: zwf
 * @since: 2024-01-17 18:07:15
 * @description: 示例mapper
 */
@Mapper
public interface TimedTaskMapper {
    @Select("select now()")
    String getDateTime();
}
