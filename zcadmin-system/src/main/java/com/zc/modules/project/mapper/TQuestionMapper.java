package com.zc.modules.project.mapper;

import com.zc.modules.project.dto.QuestionDTO;
import com.zc.modules.project.entity.TQuestion;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 问题 数据层
 *
 * @author zhangc
 * @date 2021-09-14
 */
@Mapper
public interface TQuestionMapper extends BaseMapper<TQuestion> {

    /**
     * 查询问题信息
     *
     * @param id 问题ID
     * @return 问题信息
     */
    TQuestion selectByPrimaryKey(Integer id);

    @Select("SELECT tq.*,tc.content FROM  t_question as tq left join t_text_content as tc\n" +
            "on tq.info_text_content_id=tc.id where tq.id=#{id} and tq.is_delete=0")
    QuestionDTO selectContentByPrimaryKey(Integer id);


    /**
     * 根据条件,查询问题列表
     *
     * @param record 问题信息
     * @return 问题集合
     */
    List<TQuestion> selectListBySelective(TQuestion record);

    /**
     * 根据条件,查询第一个问题对象(一般用于条件可以确定唯一数据)
     *
     * @param record 问题信息
     * @return 问题
     */
     TQuestion selectOneBySelective(TQuestion record);

    /**
     * 根据条件,分页查询问题列表
     *
     * @param record 问题信息
     * @param page mybatis-plus 分页对象
     * @return 问题集合
     */
    IPage<TQuestion> selectPageBySelective(TQuestion record, Page page);


    IPage<QuestionDTO> selectContentPageBySelective(TQuestion record, Page page);
    /**
     * 根据主键集合,批量查询问题列表
     *
     * @param ids 问题主键List集合
     * @return 问题集合
     */

    List<TQuestion> selectByPrimaryKeys(List<Integer> ids);


    List<QuestionDTO> selectContentByPrimaryKeys(List<Integer> ids);
    /**
     * 查询符合条件的语句数量
     *
     * @param record 问题 信息
     * @return 查询结果数量
     */
    int selectCountBySelective(TQuestion record);


    /**
     * 插入单条数据
     *
     * @param record 问题 信息
     * @return 插入数量
     */
    int insert(TQuestion record);
    /**
     * 条件插入单条数据
     *
     * @param record 问题 信息
     * @return 插入数量
     */
    int insertSelective(TQuestion record);
    /**
     * 批量插入多条数据
     *
     * @param recordList 问题集合
     * @return 插入数量
     */
    int insertBatch(List<TQuestion> recordList);
    /**
     * 修改单条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param record 问题 信息
     * @return 修改数量
     */
    int update(TQuestion record);
    /**
     * 根据主键修改单条数据,仅修改存在数值的属性
     *
     * @param record 问题 信息
     * @return 修改数量
     */
    int updateSelective(TQuestion record);

    /**
     * 根据条件修改某些参数,仅修改存在数值的属性
     *
     * @param params  需要修改的信息
     * @param record  根据的条件信息
     * @return 修改数量
     */
    int updateParamsBySelective(TQuestion params,TQuestion record);


    /**
     * 修改多条数据,若部分属性为null,则将数据库中的数据也修改为null
     *
     * @param recordList 问题 集合
     * @return 修改数量
     */
    int updateBatch(List<TQuestion> recordList);
    /**
     * 修改多条数据,仅修改存在数值的属性
     *
     * @param recordList 问题 集合
     * @return 修改数量
     */
    int updateBatchSelective(List<TQuestion> recordList);
    /**
     * 根据条件删除数据
     * 逻辑删除      *
     * @param record 删除的条件
     * @return 删除数量
     */
    int deleteBySelective(TQuestion record);
    /**
     * 根据数据值删除数据
     * 逻辑删除      *
     * @param id 问题 主键
     * @return 删除数量
     */
    int deleteByPrimaryKey(Integer id);


    /**
     * 根据主键集合删除数据
     * 逻辑删除      *
     * @param ids 问题 主键集合
     * @return 删除数量
     */
    int deleteByPrimaryKeys(List<Integer> ids);

}
