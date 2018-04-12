package com.kk.dao;

import com.kk.pojo.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface NewsMapper {
    @Delete({
        "delete from news",
        "where newsId = #{newsId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer newsId);

    @Insert({
        "insert into news (newsId, title, ",
        "publishDate, author, ",
        "typeId, click, isHead, ",
        "isImage, imageName, ",
        "isHot, content)",
        "values (#{newsId,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{publishDate,jdbcType=TIMESTAMP}, #{author,jdbcType=VARCHAR}, ",
        "#{typeId,jdbcType=INTEGER}, #{click,jdbcType=INTEGER}, #{isHead,jdbcType=TINYINT}, ",
        "#{isImage,jdbcType=TINYINT}, #{imageName,jdbcType=VARCHAR}, ",
        "#{isHot,jdbcType=TINYINT}, #{content,jdbcType=LONGVARCHAR})"
    })
    int insert(News record);

    int insertSelective(News record);

    @Select({
        "select",
        "newsId, title, publishDate, author, typeId, click, isHead, isImage, imageName, ",
        "isHot, content",
        "from news",
        "where newsId = #{newsId,jdbcType=INTEGER}"
    })
    @ResultMap("com.kk.dao.NewsMapper.ResultMapWithBLOBs")
    News selectByPrimaryKey(Integer newsId);

    int updateByPrimaryKeySelective(News record);

    @Update({
        "update news",
        "set title = #{title,jdbcType=VARCHAR},",
          "publishDate = #{publishDate,jdbcType=TIMESTAMP},",
          "author = #{author,jdbcType=VARCHAR},",
          "typeId = #{typeId,jdbcType=INTEGER},",
          "click = #{click,jdbcType=INTEGER},",
          "isHead = #{isHead,jdbcType=TINYINT},",
          "isImage = #{isImage,jdbcType=TINYINT},",
          "imageName = #{imageName,jdbcType=VARCHAR},",
          "isHot = #{isHot,jdbcType=TINYINT},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where newsId = #{newsId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(News record);

    @Update({
        "update news",
        "set title = #{title,jdbcType=VARCHAR},",
          "publishDate = #{publishDate,jdbcType=TIMESTAMP},",
          "author = #{author,jdbcType=VARCHAR},",
          "typeId = #{typeId,jdbcType=INTEGER},",
          "click = #{click,jdbcType=INTEGER},",
          "isHead = #{isHead,jdbcType=TINYINT},",
          "isImage = #{isImage,jdbcType=TINYINT},",
          "imageName = #{imageName,jdbcType=VARCHAR},",
          "isHot = #{isHot,jdbcType=TINYINT}",
        "where newsId = #{newsId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(News record);
}