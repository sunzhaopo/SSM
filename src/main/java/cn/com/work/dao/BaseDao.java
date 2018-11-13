package cn.com.work.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2018/11/13.
 */
public interface BaseDao<T,PK extends Serializable> {
    /**
     * 取全部记录
     * @return 全部记录实体对象的List
     */
    public abstract List<T> select();

    /**
     * 按条件查询记录
     * @param param 查询条件参数，包括WHERE条件、分页条件、排序条件
     * @return 符合条件记录的实体对象的List
     */
    public abstract List<T> selectParam(Map param);
    /**
     * 批量插入
     * @param list
     */
    public abstract int insertBatch(final List<T> list);

    /**
     * 批量修改
     * @param list
     */
    public abstract int updateBatch(final List<T> list);

    /**
     * 批量删除
     * @param list
     */
    public abstract int deleteBatch(final List<PK> list);

    /**
     * 清空表，比delete具有更高的效率，而且是从数据库中物理删除（delete是逻辑删除，被删除的记录依然占有空间）
     * <p><strong>此方法一定要慎用！</strong></p>
     * @return
     */
    public abstract int truncate();

}
